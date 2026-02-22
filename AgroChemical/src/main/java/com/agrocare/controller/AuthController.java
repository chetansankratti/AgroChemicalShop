package com.agrocare.controller;

import com.agrocare.model.LoginRequest;
import com.agrocare.service.AdminService;
import com.agrocare.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        if (adminService.login(request.getUsername(), request.getPassword())) {

            String role = adminService.getUserRole(request.getUsername());

            String token = jwtUtil.generateToken(request.getUsername(), role);

            return Map.of("token", token);
        }

        throw new RuntimeException("Invalid Credentials");
    }
}