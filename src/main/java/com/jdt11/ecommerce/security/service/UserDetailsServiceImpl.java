package com.jdt11.ecommerce.security.service;

import com.jdt11.ecommerce.entities.Customer;
import com.jdt11.ecommerce.repositories.CustomerRepo;
import com.jdt11.ecommerce.security.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " is not found!"));
        return UserDetailsImpl.build(customer);
    }
}
