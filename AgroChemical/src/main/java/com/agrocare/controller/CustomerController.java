package com.agrocare.controller;

import com.agrocare.model.Customer;
import com.agrocare.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@RequestBody Customer customer) {
        service.createCustomer(customer);
        return "Customer Created";
    }

    @GetMapping
    public List<Customer> getAll() {
        return service.getAllCustomers();
    }

    @PutMapping("/{id}/pending")
    public String updatePending(@PathVariable Long id,
                                @RequestParam Double amount) {
        service.updatePending(id, amount);
        return "Pending Updated";
    }
}