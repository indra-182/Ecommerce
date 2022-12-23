package com.jdt11.ecommerce.services;

import com.jdt11.ecommerce.entities.OrderCart;

import java.util.List;

public interface OrderCartService {
    public OrderCart addCart(String username,String productId, Integer quantity);
    public List<OrderCart> findByCustomerId(String username);
    public OrderCart updateQuantity(String username, String productId, Integer quantity);
    public void deleteCart(String username, String productId);
}
