package com.jdt11.ecommerce.repositories;

import com.jdt11.ecommerce.entities.Role;
import com.jdt11.ecommerce.entities.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
    Optional<Role> findByName(Roles name);
}
