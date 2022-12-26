package com.jdt11.ecommerce.services;

import com.jdt11.ecommerce.entities.Users;

import java.util.List;

public interface UsersService {
    public Users create(Users users);
    public List<Users> findAll();
    public Users findById(String id);
    public Users edit(Users users);
    public void deleteById(String id);
}
