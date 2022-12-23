package com.jdt11.ecommerce.entities.DTO;

import lombok.Data;

@Data
public class JwtDTO {
    private String token;
    private String username;

    public JwtDTO(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
