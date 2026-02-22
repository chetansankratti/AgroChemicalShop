package com.agrocare.controller;

import com.agrocare.model.Product;
import com.agrocare.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@RequestBody Product product) {
        service.createProduct(product);
        return "Product Added Successfully";
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteProduct(id);
        return "Product Deleted Successfully";
    }
}