package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class PizzaPostDto {

    private Long id;
    private Long order;
    private Long pizzaSize;
    private List<Long> flavors;
    private List<Long> edges;

    public PizzaPostDto() {
    }

    public PizzaPostDto(PizzaModel pizzaModel) {
        BeanUtils.copyProperties(pizzaModel, this);
    }

    public PizzaPostDto(Long id, Long order, Long pizzaSize, List<Long> flavors, List<Long> edges) {
        this.id = id;
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

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
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
