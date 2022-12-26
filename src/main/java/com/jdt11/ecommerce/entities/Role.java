package com.jdt11.ecommerce.entities;

import com.jdt11.ecommerce.entities.enums.Roles;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "mst_roles")
public class Role {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Roles name;
}
