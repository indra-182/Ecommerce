package com.jdt11.ecommerce.services.impl;

import com.jdt11.ecommerce.entities.Users;
import com.jdt11.ecommerce.exceptions.BadRequestException;
import com.jdt11.ecommerce.exceptions.NotFoundException;
import com.jdt11.ecommerce.repositories.RoleRepo;
import com.jdt11.ecommerce.repositories.UsersRepo;
import com.jdt11.ecommerce.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Users create(Users users) {
        if (usersRepo.existsById(users.getId())) {
            throw new BadRequestException("Username" + users.getId() + " is already registered!");
        }
        if (usersRepo.existsByEmail(users.getEmail())) {
            throw new BadRequestException("Email " + users.getEmail() + " is already registered!");
        }

        return usersRepo.save(users);
    }

    @Override
    public List<Users> findAll() {
        return usersRepo.findAll();
    }

    @Override
    public Users findById(String id) {
        return usersRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User id " + id + " is not found!"));
    }

    @Override
    public Users edit(Users users) {
        return usersRepo.save(users);
    }

    @Override
    public void deleteById(String id) {
        if (!usersRepo.existsById(id)) {
            throw new NotFoundException("Username ID " + id + " is not found!");
        }
        usersRepo.deleteById(id);
    }
}
