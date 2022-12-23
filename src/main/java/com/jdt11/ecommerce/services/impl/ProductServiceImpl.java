package com.jdt11.ecommerce.services.impl;

import com.jdt11.ecommerce.entities.Product;
import com.jdt11.ecommerce.exceptions.NotFoundException;
import com.jdt11.ecommerce.repositories.CategoryRepo;
import com.jdt11.ecommerce.repositories.ProductRepo;
import com.jdt11.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Product create(Product product) {
        categoryRepo.findById(product.getCategory().getId())
                        .orElseThrow(() -> new NotFoundException("Category ID " + product.getCategory().getId() + "is not found!"));
        product.setId(UUID.randomUUID().toString());
        return productRepo.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product ID " + id + " is not found!"));
    }

    @Override
    public Product edit(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void deleteById(String id) {
        if (!productRepo.existsById(id)) {
            throw new NotFoundException("Product ID " + id + " is not found!");
        }
        productRepo.deleteById(id);
    }
}
