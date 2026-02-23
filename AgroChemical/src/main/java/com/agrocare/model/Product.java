package com.agrocare.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {

    private Long id;
    private String name;
    private BigDecimal mrp;
    private BigDecimal gstPercentage;
    private Integer stockQuantity;
    private Integer lowStockLimit;
    private LocalDate expiryDate;
    private Boolean isActive;

    public Product() {}

    public Product(Long id, String name, BigDecimal mrp, BigDecimal gstPercentage,
                   Integer stockQuantity, Integer lowStockLimit,
                   LocalDate expiryDate, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.mrp = mrp;
        this.gstPercentage = gstPercentage;
        this.stockQuantity = stockQuantity;
        this.lowStockLimit = lowStockLimit;
        this.expiryDate = expiryDate;
        this.isActive = isActive;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getMrp() { return mrp; }
    public void setMrp(BigDecimal mrp) { this.mrp = mrp; }

    public BigDecimal getGstPercentage() { return gstPercentage; }
    public void setGstPercentage(BigDecimal gstPercentage) { this.gstPercentage = gstPercentage; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Integer getLowStockLimit() { return lowStockLimit; }
    public void setLowStockLimit(Integer lowStockLimit) { this.lowStockLimit = lowStockLimit; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }
}