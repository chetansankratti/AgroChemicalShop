package com.agrocare.service;

import com.agrocare.model.Admin;
import com.agrocare.repository.AdminRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository repository;

    private JdbcTemplate jdbcTemplate;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    public boolean login(String username, String password) {
        Admin admin = repository.findByUsername(username);
        return admin != null && admin.getPassword().equals(password);
    }

    public int resetPassword(String phone, String newPassword) {
        return repository.updatePassword(phone, newPassword);
    }

    public int resetPasswordByUserId(Long userId, String newPassword) {

        return jdbcTemplate.update(
                "UPDATE users SET password = ? WHERE id = ?",
                newPassword, userId
        );
    }

    public String getUserRole(String username) {

        String sql = "SELECT role FROM users WHERE username = ?";

        return jdbcTemplate.queryForObject(
                sql,
                String.class,
                username
        );
    }
}