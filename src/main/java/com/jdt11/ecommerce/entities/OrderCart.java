package com.jdt11.ecommerce.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "trx_cart")
public class OrderCart {
    @Id
    private String id;

    @Column(name = "product_quantity")
    private Integer quantity;

    private BigDecimal price;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private Users users;

}
