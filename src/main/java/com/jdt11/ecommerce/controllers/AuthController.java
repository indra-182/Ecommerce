package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.DTO.JwtDTO;
import com.jdt11.ecommerce.entities.DTO.LoginDTO;
import com.jdt11.ecommerce.entities.DTO.RegisterDTO;
import com.jdt11.ecommerce.entities.Role;
import com.jdt11.ecommerce.entities.Users;
import com.jdt11.ecommerce.entities.enums.Roles;
import com.jdt11.ecommerce.repositories.RoleRepo;
import com.jdt11.ecommerce.security.component.JWTUtils;
import com.jdt11.ecommerce.security.domain.UserDetailsImpl;
import com.jdt11.ecommerce.security.service.UserDetailsServiceImpl;
import com.jdt11.ecommerce.services.RoleService;
import com.jdt11.ecommerce.services.UsersService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // User Sign Up
    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody RegisterDTO registerDTO) {
        Users users = new Users();
        users.setId(registerDTO.getUsername());
        users.setEmail(registerDTO.getEmail());
        users.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        users.setName(registerDTO.getName());
        Set<String> strRoles = registerDTO.getRole();
        Set<Role> rolez = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepo.findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role is not found!"));
            rolez.add(userRole);
        } else {
            Role adminRole = roleRepo.findByName(Roles.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Role is not found!"));
            rolez.add(adminRole);
        }
        users.setRoles(rolez);
        Users userz = usersService.create(users);
        return ResponseEntity.ok(userz);
    }

    //User Login
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> authUser(@RequestBody LoginDTO loginDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtUtils.generateToken(auth);
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok().body(new JwtDTO(token, user.getUsername(), roles));
    }

    @PostMapping("decode")
    public String decode(@RequestBody String token) throws UnsupportedEncodingException {
        String payload = token.split("\\.")[1];
        return new String(Base64.decodeBase64(payload), "UTF-8");
    }
}
