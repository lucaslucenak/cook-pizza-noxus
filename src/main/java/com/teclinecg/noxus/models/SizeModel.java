package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.PizzaSizeEnum;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Table(name = "size")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class SizeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PizzaSizeEnum size;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public SizeModel() {
    }

    public SizeModel(PizzaSizeEnum size) {
        this.size = size;
    }

    public SizeModel(Long id, PizzaSizeEnum size, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.size = size;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PizzaSizeEnum getPizzaSize() {
        return size;
    }

    public void setPizzaSize(PizzaSizeEnum pizzaSize) {
        this.size = pizzaSize;
    }

    public PizzaSizeEnum getSize() {
        return size;
    }

    public void setSize(PizzaSizeEnum size) {
        this.size = size;
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
        SizeModel sizeModel = (SizeModel) o;
        return id.equals(sizeModel.id) && size == sizeModel.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size);
    }
}
