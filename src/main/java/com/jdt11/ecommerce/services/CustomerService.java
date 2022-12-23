package com.jdt11.ecommerce.services;

import com.jdt11.ecommerce.entities.Customer;

import java.util.List;

public interface CustomerService {
    public Customer create(Customer customer);
    public List<Customer> findAll();
    public Customer findById(String id);
    public Customer edit(Customer customer);
    public void deleteById(String id);
}
