package com.agrocare.service;

import com.agrocare.model.Product;
import com.agrocare.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public int createProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public int deleteProduct(Long id) {
        return repository.deleteById(id);
    }
}