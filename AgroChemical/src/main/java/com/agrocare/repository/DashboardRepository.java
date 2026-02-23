package com.agrocare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getDashboardStats() {

        return jdbcTemplate.queryForMap("""
            SELECT
                (SELECT COALESCE(SUM(total_amount),0) FROM bills) AS total_sales,
                (SELECT COALESCE(SUM(balance_amount),0)
                 FROM bills WHERE status='PENDING') AS total_pending
        """);
    }
}