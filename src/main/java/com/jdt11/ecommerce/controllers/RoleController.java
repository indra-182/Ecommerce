package com.jdt11.ecommerce.controllers;

import com.jdt11.ecommerce.entities.Role;
import com.jdt11.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/createRole")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @GetMapping("/findRoles")
    public List<Role> findAll() {
        return roleService.findAll();
    }
}
