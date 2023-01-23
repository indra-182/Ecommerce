package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.DTO.ResponseDTO;
import com.jdt11.ecommerce.entities.Users;
import com.jdt11.ecommerce.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Create User
    @PostMapping("/customer")
    public ResponseEntity<ResponseDTO<Users>> create(@Valid @RequestBody Users users, Errors errors) {
        ResponseDTO<Users> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(usersService.create(users));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    // Show All User
    @GetMapping("/customer")
    public List<Users> findAll() {
        return usersService.findAll();
    }

    // Find Customer by Customer ID
    @GetMapping("/customer/{id}")
    public Users findById(@PathVariable("id") String id) {
        return usersService.findById(id);
    }

    // Edit Customer
    @PutMapping("/customer")
    public ResponseEntity<ResponseDTO<Users>> edit(@Valid @RequestBody Users users, Errors errors) {
        ResponseDTO<Users> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(usersService.edit(users));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    // Delete Customer by ID
    @DeleteMapping("/customer/{id}")
    public void deleteById(@PathVariable("id") String id) {
        usersService.deleteById(id);
    }
}
