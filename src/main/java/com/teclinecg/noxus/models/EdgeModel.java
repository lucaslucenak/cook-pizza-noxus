package com.teclinecg.noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teclinecg.noxus.dtos.EdgeDto;
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
@Table(name = "edge")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class EdgeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String edge;
    @Column(nullable = false)
    private Double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "edges")
    private List<PizzaModel> pizzas;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public EdgeModel() {
    }

    public EdgeModel(EdgeDto edgeDto) {
        BeanUtils.copyProperties(edgeDto, this);
    }

    public EdgeModel(Long id, String edge, Double price, List<PizzaModel> pizzas) {
        this.id = id;
        this.edge = edge;
        this.price = price;
        this.pizzas = pizzas;
    }

    public EdgeModel(Long id, String edge, Double price, List<PizzaModel> pizzas, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.edge = edge;
        this.price = price;
        this.pizzas = pizzas;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEdge() {
        return edge;
    }

    public void setEdge(String edge) {
        this.edge = edge;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<PizzaModel> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaModel> pizzas) {
        this.pizzas = pizzas;
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
        EdgeModel edgeModel = (EdgeModel) o;
        return id.equals(edgeModel.id) && Objects.equals(edge, edgeModel.edge) && Objects.equals(price, edgeModel.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edge, price);
    }
}
