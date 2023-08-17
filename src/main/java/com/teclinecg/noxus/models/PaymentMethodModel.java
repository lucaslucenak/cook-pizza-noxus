package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Table(name = "payment_method")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class PaymentMethodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethodEnum paymentMethod;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public PaymentMethodModel() {
    }

    public PaymentMethodModel(Long id, PaymentMethodEnum paymentMethod, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
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
        PaymentMethodModel that = (PaymentMethodModel) o;
        return id.equals(that.id) && paymentMethod == that.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod);
    }
}
