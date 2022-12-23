package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.Customer;
import com.jdt11.ecommerce.entities.DTO.JwtDTO;
import com.jdt11.ecommerce.entities.DTO.LoginDTO;
import com.jdt11.ecommerce.entities.DTO.RegisterDTO;
import com.jdt11.ecommerce.entities.DTO.ResponseDTO;
import com.jdt11.ecommerce.security.component.JWTUtils;
import com.jdt11.ecommerce.security.domain.UserDetailsImpl;
import com.jdt11.ecommerce.security.service.UserDetailsServiceImpl;
import com.jdt11.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomerService customerService;

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
    public ResponseEntity<Customer> register( @RequestBody RegisterDTO registerDTO) {
        Customer customer = new Customer();
        customer.setId(registerDTO.getUsername());
        customer.setEmail(registerDTO.getEmail());
        customer.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        customer.setName(registerDTO.getName());
        Customer cust = customerService.create(customer);
        return ResponseEntity.ok(cust);
    }

    //User Login
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> authUser(@RequestBody LoginDTO loginDTO,Errors errors) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext()
                .setAuthentication(auth);
        String token = jwtUtils.generateToken(auth);
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.ok().body(new JwtDTO(token, user.getUsername()));
    }
}
