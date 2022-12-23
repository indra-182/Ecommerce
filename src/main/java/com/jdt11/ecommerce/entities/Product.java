package com.jdt11.ecommerce.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "mst_product")
public class Product {
    @Id
    private String id;

    @NotEmpty(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    private Integer price;

    @NotNull(message = "Stock is required")
    private Integer stock;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "merchant_id")
    @ManyToOne
    private Merchant merchant;
}
