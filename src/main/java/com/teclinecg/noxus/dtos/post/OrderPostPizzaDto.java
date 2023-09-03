package com.teclinecg.noxus.dtos.post;

import com.teclinecg.noxus.models.SizeModel;

import java.util.List;
import java.util.Map;

public class OrderPostPizzaDto {
    private Long pizzaSize;
    private List<Long> flavors;
    private List<Long> edges;
    private Integer quantity;

    public OrderPostPizzaDto() {
    }

    public OrderPostPizzaDto(Long pizzaSize, List<Long> flavors, List<Long> edges, Integer quantity) {
        this.pizzaSize = pizzaSize;
        this.flavors = flavors;
        this.edges = edges;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
