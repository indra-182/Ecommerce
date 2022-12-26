package com.jdt11.ecommerce.entities.DTO;

import lombok.Data;

import java.util.List;

@Data
public class JwtDTO {
    private String token;
    private String username;
    private List<String> roles;

    public JwtDTO(String token, String username,List<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }
}
