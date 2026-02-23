package com.agrocare.controller;

import com.agrocare.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardRepository repository;

    @GetMapping("/stats")
    public Map<String,Object> stats() {
        return repository.getDashboardStats();
    }
}