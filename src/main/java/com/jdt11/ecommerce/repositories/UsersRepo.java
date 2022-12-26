package com.jdt11.ecommerce.repositories;

import com.jdt11.ecommerce.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,String > {
    boolean existsByEmail(String email);
}
