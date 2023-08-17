package com.teclinecg.noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teclinecg.noxus.dtos.DrinkDto;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Table(name = "drink")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class DrinkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @ManyToMany(mappedBy = "drinks", fetch = FetchType.EAGER)
    private List<OrderModel> orders;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public DrinkModel() {
    }

    public DrinkModel(DrinkDto drinkDto) {
        BeanUtils.copyProperties(drinkDto, this);
    }

    public DrinkModel(Long id, String name, Double price, List<OrderModel> orders) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orders = orders;
    }

    public DrinkModel(Long id, String name, Double price, List<OrderModel> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orders = orders;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }

    public void addOrder(OrderModel orderModel) {
        orders.add(orderModel);
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
        DrinkModel that = (DrinkModel) o;
        return id.equals(that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, orders);
    }
}
