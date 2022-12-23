package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.Category;
import com.jdt11.ecommerce.entities.DTO.ResponseDTO;
import com.jdt11.ecommerce.entities.Merchant;
import com.jdt11.ecommerce.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/merchant")
    public ResponseEntity<ResponseDTO<Merchant>> create(@Valid @RequestBody Merchant merchant, Errors errors) {
        ResponseDTO<Merchant> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(merchantService.create(merchant));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/merchant")
    public List<Merchant> findAll() {
        return merchantService.findAll();
    }

    @GetMapping("/merchant/{id}")
    public Merchant findById(@PathVariable("id") String id) {
        return merchantService.findById(id);
    }

    @PutMapping("/merchant")
    public ResponseEntity<ResponseDTO<Merchant>> edit(@Valid @RequestBody Merchant merchant, Errors errors) {
        ResponseDTO<Merchant> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(merchantService.edit(merchant));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/merchant/{id}")
    public void deleteById(@PathVariable("id") String id) {
        merchantService.deleteById(id);
    }
}
