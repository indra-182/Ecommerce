package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.Customer;
import com.jdt11.ecommerce.entities.DTO.ResponseDTO;
import com.jdt11.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Create User
    @PostMapping("/customer")
    public ResponseEntity<ResponseDTO<Customer>> create(@Valid @RequestBody Customer customer, Errors errors) {
        ResponseDTO<Customer> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(customerService.create(customer));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    // Show All User
    @GetMapping("/customer")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    // Find Customer by Customer ID
    @GetMapping("/customer/{id}")
    public Customer findById(@PathVariable("id") String id) {
        return customerService.findById(id);
    }

    // Edit Customer
    @PutMapping("/customer")
    public ResponseEntity<ResponseDTO<Customer>> edit(@Valid @RequestBody Customer customer, Errors errors) {
        ResponseDTO<Customer> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(customerService.edit(customer));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    // Delete Customer by ID
    @DeleteMapping("/customer/{id}")
    public void deleteById(@PathVariable("id") String id) {
        customerService.deleteById(id);
    }
}
