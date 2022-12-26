package com.jdt11.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "mst_customer")
@NoArgsConstructor
public class Users {
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

    @ManyToMany
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Users(String username) {
        this.id = username;
    }
}
