package com.jdt11.ecommerce.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mst_category")
@Data
public class Category {
    @Id
    private String id;

    @NotEmpty(message = "Name is required")
    private String name;
}
