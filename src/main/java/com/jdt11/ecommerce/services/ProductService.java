package com.jdt11.ecommerce.services;

import com.jdt11.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public Product findById(String id);
    public Product edit(Product product);
    public void deleteById(String id);
}
