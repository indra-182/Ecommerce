package com.jdt11.ecommerce.security.service;

import com.jdt11.ecommerce.entities.Users;
import com.jdt11.ecommerce.repositories.UsersRepo;
import com.jdt11.ecommerce.security.domain.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " is not found!"));
        return UserDetailsImpl.build(users);
    }
}
