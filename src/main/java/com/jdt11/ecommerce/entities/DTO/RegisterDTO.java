package com.jdt11.ecommerce.entities.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterDTO {
    private String username;
    private String name;
    private String password;
    private String email;
    private Set<String> role;
}
