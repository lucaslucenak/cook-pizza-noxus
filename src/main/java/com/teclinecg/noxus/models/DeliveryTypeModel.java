package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.DeliveryTypeEnum;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Table(name = "delivery_type")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class DeliveryTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryTypeEnum deliveryType;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public DeliveryTypeModel() {
    }

    public DeliveryTypeModel(Long id, DeliveryTypeEnum deliveryType) {
        this.id = id;
        this.deliveryType = deliveryType;
    }

    public DeliveryTypeModel(Long id, DeliveryTypeEnum deliveryType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.deliveryType = deliveryType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryTypeEnum getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryTypeEnum deliveryType) {
        this.deliveryType = deliveryType;
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
        DeliveryTypeModel that = (DeliveryTypeModel) o;
        return id.equals(that.id) && deliveryType == that.deliveryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryType);
    }
}
