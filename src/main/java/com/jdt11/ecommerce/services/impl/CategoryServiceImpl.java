package com.jdt11.ecommerce.services.impl;

import com.jdt11.ecommerce.entities.Category;
import com.jdt11.ecommerce.exceptions.NotFoundException;
import com.jdt11.ecommerce.repositories.CategoryRepo;
import com.jdt11.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public Category create(Category category) {
        category.setId(UUID.randomUUID().toString());
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findById(String id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Category ID " + id + " is not found!"));
    }

    @Override
    public Category edit(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void deleteById(String id) {
        if (!categoryRepo.existsById(id)) {
            throw new NotFoundException("Category ID " + id + " is not found!");
        }
        categoryRepo.deleteById(id);
    }
}
