package com.jdt11.ecommerce.repositories;

import com.jdt11.ecommerce.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepo extends JpaRepository<Merchant,String > {
}
