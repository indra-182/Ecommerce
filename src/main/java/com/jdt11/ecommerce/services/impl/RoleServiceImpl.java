package com.jdt11.ecommerce.services.impl;


import com.jdt11.ecommerce.entities.Role;
import com.jdt11.ecommerce.repositories.RoleRepo;
import com.jdt11.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role createRole(Role role) {
        role.setId(UUID.randomUUID().toString());
        role.setName(role.getName());
        return roleRepo.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

}
