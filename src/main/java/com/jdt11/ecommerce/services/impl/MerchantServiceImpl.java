package com.jdt11.ecommerce.services.impl;

import com.jdt11.ecommerce.entities.Merchant;
import com.jdt11.ecommerce.exceptions.NotFoundException;
import com.jdt11.ecommerce.repositories.MerchantRepo;
import com.jdt11.ecommerce.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepo merchantRepo;

    @Override
    public Merchant create(Merchant merchant) {
        merchant.setId(UUID.randomUUID().toString());
        return merchantRepo.save(merchant);
    }

    @Override
    public List<Merchant> findAll() {
        return merchantRepo.findAll();
    }

    @Override
    public Merchant findById(String id) {
        return merchantRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Merchant ID " + id + " is not found!"));
    }

    @Override
    public Merchant edit(Merchant merchant) {
        return merchantRepo.save(merchant);
    }

    @Override
    public void deleteById(String id) {
        if (!merchantRepo.existsById(id)) {
            throw new NotFoundException("Merchant ID " + id + " is not found!");
        }
        merchantRepo.deleteById(id);
    }
}
