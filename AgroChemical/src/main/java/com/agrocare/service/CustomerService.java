package com.agrocare.service;

import com.agrocare.model.Customer;
import com.agrocare.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public int createCustomer(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public int updatePending(Long id, Double amount) {
        return repository.updatePendingAmount(id, amount);
    }
}