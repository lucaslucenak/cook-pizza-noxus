package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class PizzaPostDto {

    private Long id;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;
    private OrderModel order;
    private Long pizzaSize;
    private List<Long> flavors;
    private List<Long> edges;

    public PizzaPostDto() {
    }

    public PizzaPostDto(PizzaModel pizzaModel) {
        BeanUtils.copyProperties(pizzaModel, this);
    }

    public PizzaPostDto(Long id, Double price, OrderModel order, Long pizzaSize, List<Long> flavors, List<Long> edges) {
        this.id = id;
        this.price = price;
        this.order = order;
        this.pizzaSize = pizzaSize;
        this.flavors = flavors;
        this.edges = edges;
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

    public Long getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(Long pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public List<Long> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<Long> flavors) {
        this.flavors = flavors;
    }

    public List<Long> getEdges() {
        return edges;
    }

    public void setEdges(List<Long> edges) {
        this.edges = edges;
    }
}
