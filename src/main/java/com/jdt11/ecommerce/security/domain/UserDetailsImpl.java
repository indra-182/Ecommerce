package com.jdt11.ecommerce.security.domain;

import com.jdt11.ecommerce.entities.Customer;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String name;
    private String email;
    private String password;

    public UserDetailsImpl(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //getId 'cause i assigned id = username
    // this method is called in UserDetailsServiceImpl
    public static UserDetailsImpl build(Customer customer) {
        return new UserDetailsImpl(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
