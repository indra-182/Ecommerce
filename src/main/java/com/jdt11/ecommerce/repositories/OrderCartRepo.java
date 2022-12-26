package com.jdt11.ecommerce.repositories;

import com.jdt11.ecommerce.entities.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderCartRepo extends JpaRepository<OrderCart,String > {
    Optional<OrderCart> findByUsersIdAndProductId(String username, String productId);

    List<OrderCart> findByUsersId(String username);
}
