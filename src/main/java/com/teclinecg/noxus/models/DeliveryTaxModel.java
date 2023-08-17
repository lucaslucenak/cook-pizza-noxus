package com.teclinecg.noxus.models;


import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Table(name = "delivery_tax")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class DeliveryTaxModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tax;
    private String neighbourhood;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public DeliveryTaxModel(Long id, Double tax, String neighbourhood) {
        this.id = id;
        this.tax = tax;
        this.neighbourhood = neighbourhood;
    }

    public DeliveryTaxModel(Long id, Double tax, String neighbourhood, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.tax = tax;
        this.neighbourhood = neighbourhood;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DeliveryTaxModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryTaxModel that = (DeliveryTaxModel) o;
        return id.equals(that.id) && Objects.equals(tax, that.tax) && Objects.equals(neighbourhood, that.neighbourhood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tax, neighbourhood);
    }
}
