package com.rushabh.microservices.fraud.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_check_history")
public class FraudCheckHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "customerId", unique = true)
    private Integer customerId;
    @Column(name = "is_fraudster")
    private Boolean isFraudster;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public FraudCheckHistory() {
        super();
    }

    @PrePersist
    protected void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Boolean getFraudster() {
        return isFraudster;
    }

    public void setFraudster(Boolean fraudster) {
        isFraudster = fraudster;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
