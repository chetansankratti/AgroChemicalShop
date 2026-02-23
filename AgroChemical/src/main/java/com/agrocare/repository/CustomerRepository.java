package com.agrocare.repository;

import com.agrocare.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Customer customer) {
        String sql = """
            INSERT INTO customers (name, phone, address, pending_amount)
            VALUES (?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                customer.getName(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getPendingAmount()
        );
    }

    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM customers",
                (rs, rowNum) -> {
                    Customer c = new Customer();
                    c.setId(rs.getLong("id"));
                    c.setName(rs.getString("name"));
                    c.setPhone(rs.getString("phone"));
                    c.setAddress(rs.getString("address"));
                    c.setPendingAmount(rs.getDouble("pending_amount"));
                    return c;
                });
    }

    public int updatePendingAmount(Long id, Double amount) {
        String sql = "UPDATE customers SET pending_amount = ? WHERE id = ?";
        return jdbcTemplate.update(sql, amount, id);
    }
}