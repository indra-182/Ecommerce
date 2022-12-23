package com.jdt11.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "mst_customer")
@NoArgsConstructor
public class Customer {
    @Id
    @NotEmpty(message = "Username is required")
    private String id;

    @NotEmpty(message = "Name is required")
    private String name;

    @JsonIgnore
    private String phoneNumber;

    @JsonIgnore
    private String address;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid!")
    private String email;

    @JsonIgnore
    private String password;

    public Customer(String username) {
        this.id = username;
    }
}
