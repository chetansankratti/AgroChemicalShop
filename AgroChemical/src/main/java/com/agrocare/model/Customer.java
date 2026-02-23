package com.agrocare.model;

import java.time.LocalDateTime;

public class Customer {

    private Long id;
    private String name;
    private String phone;
    private String address;
    private Double pendingAmount;
    private LocalDateTime createdAt;

    public Customer() {}

    public Customer(Long id, String name, String phone, String address,
                    Double pendingAmount, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.pendingAmount = pendingAmount;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getPendingAmount() { return pendingAmount; }
    public void setPendingAmount(Double pendingAmount) { this.pendingAmount = pendingAmount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}