package com.jdt11.ecommerce.entities.DTO;

import lombok.Data;


@Data
public class CartDTO {
    private String productId;
    private Integer quantity;
}
