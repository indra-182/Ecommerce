package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.DTO.CartDTO;
import com.jdt11.ecommerce.entities.DTO.ResponseDTO;
import com.jdt11.ecommerce.entities.OrderCart;
import com.jdt11.ecommerce.security.domain.UserDetailsImpl;
import com.jdt11.ecommerce.services.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class OrderCartController {

    @Autowired
    private OrderCartService cartService;

    // Create Cart
    @PostMapping("/cart")
    public ResponseEntity<ResponseDTO<OrderCart>> addCart(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody CartDTO dto, Errors errors) {
        ResponseDTO<OrderCart> response = new ResponseDTO<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                response.getMessages().add(e.getDefaultMessage());
            }
            response.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        response.setPayload(cartService.addCart(user.getUsername(), dto.getProductId(), dto.getQuantity()));
        response.getMessages().add("SUCCESS");
        return ResponseEntity.ok(response);
    }

    // Show Carts by Customer ID
    @GetMapping("/cart")
    public List<OrderCart> findByCustomerId(@AuthenticationPrincipal UserDetailsImpl user) {
        return cartService.findByCustomerId(user.getUsername());
    }

    // Update Cart's Quantity by Product ID, checked by jwt token
    @PatchMapping("/cart/{productId}")
    public OrderCart updateQuantity(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("productId") String productId,
                            @RequestParam("quantity") Integer quantity) {
        return cartService.updateQuantity(user.getUsername(), productId, quantity);
    }

    // Delete Carts by Product ID
    @DeleteMapping("/cart/{productId}")
    public void deleteCart(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("productId") String productId) {
        cartService.deleteCart(user.getUsername(), productId);
    }
}
