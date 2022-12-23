package com.jdt11.ecommerce.services;


import com.jdt11.ecommerce.entities.Category;

import java.util.List;

public interface CategoryService {

    public Category create(Category category);
    public List<Category> findAll();
    public Category findById(String id);
    public Category edit(Category category);
    public void deleteById(String id);
}
