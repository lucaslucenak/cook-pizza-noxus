package com.teclinecg.noxus.models;

import com.teclinecg.noxus.dtos.PizzaDto;
import com.teclinecg.noxus.dtos.post.PizzaPostDto;
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
@Table(name = "pizza")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class PizzaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "pizza_size_id", nullable = false)
    private SizeModel pizzaSize;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pizza_flavor",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "flavor_id")
    )
    private List<FlavorModel> flavors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pizza_edge",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "edge_id")
    )
    private List<EdgeModel> edges;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public PizzaModel() {
    }

    public PizzaModel(PizzaDto pizzaDto) {
        BeanUtils.copyProperties(pizzaDto, this);
    }

    public PizzaModel(PizzaPostDto pizzaPostDto) {
        BeanUtils.copyProperties(pizzaPostDto, this);
    }

    public PizzaModel(Long id, Double price, OrderModel order, SizeModel pizzaSize, List<FlavorModel> flavors, List<EdgeModel> edges, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.price = price;
        this.order = order;
        this.pizzaSize = pizzaSize;
        this.flavors = flavors;
        this.edges = edges;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public SizeModel getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(SizeModel pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public List<FlavorModel> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<FlavorModel> flavors) {
        this.flavors = flavors;
    }

    public List<EdgeModel> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeModel> edges) {
        this.edges = edges;
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
        PizzaModel that = (PizzaModel) o;
        return id.equals(that.id) && Objects.equals(price, that.price) && Objects.equals(order, that.order) && Objects.equals(pizzaSize, that.pizzaSize) && Objects.equals(flavors, that.flavors) && Objects.equals(edges, that.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, order, pizzaSize, flavors, edges);
    }
}
