package com.jdt11.ecommerce.services.impl;

import com.jdt11.ecommerce.entities.Customer;
import com.jdt11.ecommerce.entities.OrderCart;
import com.jdt11.ecommerce.entities.Product;
import com.jdt11.ecommerce.exceptions.NotFoundException;
import com.jdt11.ecommerce.repositories.OrderCartRepo;
import com.jdt11.ecommerce.repositories.ProductRepo;
import com.jdt11.ecommerce.services.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderCartServiceImpl implements OrderCartService {

    @Autowired
    private OrderCartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public OrderCart addCart(String username, String productId, Integer quantity) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product ID " + productId + " is not found!"));
        Optional<OrderCart> checkData = cartRepo.findByCustomerIdAndProductId(username,productId);
        OrderCart cart;
        // product already in database?
        if (checkData.isPresent()) {
            cart = checkData.get();
            cart.setQuantity(cart.getQuantity() + quantity);
            cart.setPrice(new BigDecimal(cart.getProduct().getPrice() * cart.getQuantity()));
            cartRepo.save(cart);
        } else {
            cart = new OrderCart();
            cart.setId(UUID.randomUUID().toString());
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cart.setPrice(new BigDecimal(cart.getProduct().getPrice() * cart.getQuantity()));
            cart.setCustomer(new Customer(username));
            cartRepo.save(cart);
        }

        return cart;
    }

    @Override
    public List<OrderCart> findByCustomerId(String username) {
        List<OrderCart> checkId = cartRepo.findByCustomerId(username);
        if (checkId.isEmpty()) {
            throw new UsernameNotFoundException("Customer ID " + username + " is not found");
        }
        return checkId;
    }

    @Override
    public OrderCart updateQuantity(String username, String productId, Integer quantity) {
        OrderCart data = cartRepo.findByCustomerIdAndProductId(username,productId)
                .orElseThrow(() -> new NotFoundException("Product ID " + productId + " is not found!"));
        data.setQuantity(quantity);
        data.setPrice(new BigDecimal(data.getProduct().getPrice() * data.getQuantity()));
        return data;
    }

    @Override
    public void deleteCart(String username, String productId) {
        OrderCart cart = cartRepo.findByCustomerIdAndProductId(username,productId)
                .orElseThrow(() -> new NotFoundException("Product ID " + productId + " is not found!"));
        cartRepo.delete(cart);
    }
}
