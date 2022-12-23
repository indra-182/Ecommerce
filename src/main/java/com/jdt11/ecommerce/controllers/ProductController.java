package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.DTO.ResponseDTO;
import com.jdt11.ecommerce.entities.Product;
import com.jdt11.ecommerce.services.ProductService;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create Product
    @PostMapping("/product")
    public ResponseEntity<ResponseDTO<Product>> create(@Valid @RequestBody Product product, Errors errors) {
        ResponseDTO<Product> response = new ResponseDTO<>();
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(productService.create(product));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    // Show All Product
    @GetMapping("/product")
    public List<Product> findAll() {
        return productService.findAll();
    }

    // Find Product by Product ID
    @GetMapping("/product/{id}")
    public Product findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    // Edit Product
    @PutMapping("/product")
    public ResponseEntity<ResponseDTO<Product>> edit(@Valid Product product, Errors errors) {
        ResponseDTO<Product> response = new ResponseDTO<>();
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(productService.edit(product));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    // Delete Product
    @DeleteMapping("product/{id}")
    public void deleteById(@PathVariable("id") String id) {
        productService.deleteById(id);
    }
}
