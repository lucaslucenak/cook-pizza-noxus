package com.teclinecg.noxus.models;

import com.teclinecg.noxus.dtos.PizzaDtoDefault;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "pizza")
public class PizzaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToOne
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "pizza_size_id")
    private SizeModel pizzaSize;

    @ManyToMany
    @JoinTable(
            name = "pizza_flavor",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "flavor_id")
    )
    private List<FlavorModel> flavors;

    @ManyToMany
    @JoinTable(
            name = "pizza_edge",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "edge_id")
    )
    private List<EdgeModel> edges;

    public PizzaModel() {
    }

    public PizzaModel(PizzaDtoDefault pizzaDto) {
        BeanUtils.copyProperties(pizzaDto, this);
    }

    public PizzaModel(Long id, Double price, OrderModel order, SizeModel pizzaSize, List<FlavorModel> flavors, List<EdgeModel> edges) {
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
        PizzaModel that = (PizzaModel) o;
        return id.equals(that.id) && Objects.equals(price, that.price) && Objects.equals(order, that.order) && Objects.equals(pizzaSize, that.pizzaSize) && Objects.equals(flavors, that.flavors) && Objects.equals(edges, that.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, order, pizzaSize, flavors, edges);
    }
}
