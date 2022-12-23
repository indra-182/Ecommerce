package com.jdt11.ecommerce.services.impl;

import com.jdt11.ecommerce.entities.Customer;
import com.jdt11.ecommerce.exceptions.BadRequestException;
import com.jdt11.ecommerce.exceptions.NotFoundException;
import com.jdt11.ecommerce.repositories.CustomerRepo;
import com.jdt11.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Customer create(Customer customer) {
        if (customerRepo.existsById(customer.getId())) {
            throw new BadRequestException("Username" + customer.getId() + " is already registered!");
        }
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new BadRequestException("Email " + customer.getEmail() + " is already registered!");
        }
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer findById(String id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User id " + id + " is not found!"));
    }

    @Override
    public Customer edit(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public void deleteById(String id) {
        if (!customerRepo.existsById(id)) {
            throw new NotFoundException("Username ID " + id + " is not found!");
        }
        customerRepo.deleteById(id);
    }
}
