package com.agrocare.repository;

import com.agrocare.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Product product) {
        String sql = """
            INSERT INTO products 
            (name, mrp, gst_percentage, stock_quantity, low_stock_limit, expiry_date, is_active)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        return jdbcTemplate.update(sql,
                product.getName(),
                product.getMrp(),
                product.getGstPercentage(),
                product.getStockQuantity(),
                product.getLowStockLimit(),
                product.getExpiryDate(),
                product.getIsActive()
        );
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Product p = new Product();
            p.setId(rs.getLong("id"));
            p.setName(rs.getString("name"));
            p.setMrp(rs.getBigDecimal("mrp"));
            p.setGstPercentage(rs.getBigDecimal("gst_percentage"));
            p.setStockQuantity(rs.getInt("stock_quantity"));
            p.setLowStockLimit(rs.getInt("low_stock_limit"));

            if (rs.getDate("expiry_date") != null) {
                p.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
            }

            p.setIsActive(rs.getBoolean("is_active"));
            return p;
        });
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}