package com.jdt11.ecommerce.services;

import com.jdt11.ecommerce.entities.Merchant;

import java.util.List;

public interface MerchantService {
    public Merchant create(Merchant merchant);
    public List<Merchant> findAll();
    public Merchant findById(String id);
    public Merchant edit(Merchant merchant);
    public void deleteById(String id);
}
