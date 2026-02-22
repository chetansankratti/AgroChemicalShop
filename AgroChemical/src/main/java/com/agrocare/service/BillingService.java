package com.agrocare.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement
@Service
public class BillingService {

    private final JdbcTemplate jdbcTemplate;

    public BillingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void createBill(Long customerId,
                           Long productId,
                           int quantity,
                           double paidAmount) {

        Double price = jdbcTemplate.queryForObject(
                "SELECT mrp FROM products WHERE id=?",
                Double.class, productId);

        double total = price * quantity;
        double pending = total - paidAmount;

        // Insert bill
        jdbcTemplate.update("""
            INSERT INTO bills (customer_id, total_amount, paid_amount, pending_amount)
            VALUES (?, ?, ?, ?)
        """, customerId, total, paidAmount, pending);

        // Update stock
        jdbcTemplate.update("""
            UPDATE products
            SET stock_quantity = stock_quantity - ?
            WHERE id = ?
        """, quantity, productId);

        // Update customer pending
        jdbcTemplate.update("""
            UPDATE customers
            SET pending_amount = pending_amount + ?
            WHERE id = ?
        """, pending, customerId);
    }
}