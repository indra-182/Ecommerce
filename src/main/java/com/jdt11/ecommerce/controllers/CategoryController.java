package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.Category;
import com.jdt11.ecommerce.entities.DTO.ResponseDTO;
import com.jdt11.ecommerce.services.CategoryService;
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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Create Category
    @PostMapping("/category")
    public ResponseEntity<ResponseDTO<Category>> create(@Valid @RequestBody Category category, Errors errors) {
        ResponseDTO<Category> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(categoryService.create(category));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    // Show All Category
    @GetMapping("/category")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    // Find Category by Category ID
    @GetMapping("/category/{id}")
    public Category findById(@PathVariable("id") String id) {
        return categoryService.findById(id);
    }

    // Edit Category
    @PutMapping("/category")
    public ResponseEntity<ResponseDTO<Category>> edit(@Valid @RequestBody Category category, Errors errors) {
        ResponseDTO<Category> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(categoryService.edit(category));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    // Delete Category by Id
    @DeleteMapping("/category/{id}")
    public void deleteById(@PathVariable("id") String id) {
        categoryService.deleteById(id);
    }
}
