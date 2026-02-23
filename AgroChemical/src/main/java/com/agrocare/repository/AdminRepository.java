package com.agrocare.repository;

import com.agrocare.model.Admin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    private final JdbcTemplate jdbcTemplate;

    public AdminRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Admin findByUsername(String username) {
        String sql = "SELECT * FROM admins WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username},
                (rs, rowNum) -> {
                    Admin a = new Admin();
                    a.setId(rs.getLong("id"));
                    a.setUsername(rs.getString("username"));
                    a.setPassword(rs.getString("password"));
                    a.setPhone(rs.getString("phone"));
                    return a;
                });
    }

    public int updatePassword(String phone, String password) {
        return jdbcTemplate.update(
                "UPDATE admins SET password=? WHERE phone=?",
                password, phone);
    }
}