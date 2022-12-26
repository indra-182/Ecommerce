package com.jdt11.ecommerce.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "mst_merchant")
public class Merchant {
    @Id
    private String id;

    private String name;

    private String phoneNumber;

    private String address;

    private String email;

    private String password;


}
