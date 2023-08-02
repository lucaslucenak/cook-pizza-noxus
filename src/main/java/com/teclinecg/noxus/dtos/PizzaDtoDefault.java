package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class PizzaDtoDefault {

    private Long id;
    private Double price;
    private OrderModel order;
    private SizeModel pizzaSize;
    private List<FlavorModel> flavors;
    private List<EdgeModel> edges;

    public PizzaDtoDefault() {
    }

    public PizzaDtoDefault(PizzaModel pizzaModel) {
        BeanUtils.copyProperties(pizzaModel, this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaDtoDefault that = (PizzaDtoDefault) o;
        return id.equals(that.id) && Objects.equals(price, that.price) && Objects.equals(order, that.order) && Objects.equals(pizzaSize, that.pizzaSize) && Objects.equals(flavors, that.flavors) && Objects.equals(edges, that.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, order, pizzaSize, flavors, edges);
    }
}
