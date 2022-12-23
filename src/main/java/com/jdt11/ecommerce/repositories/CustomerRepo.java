package com.jdt11.ecommerce.repositories;

import com.jdt11.ecommerce.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,String > {
    boolean existsByEmail(String email);
}
