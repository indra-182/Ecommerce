package com.jdt11.ecommerce.services;

import com.jdt11.ecommerce.entities.Role;

import java.util.List;

public interface RoleService {

    public Role createRole(Role role);
    public List<Role> findAll();

}
