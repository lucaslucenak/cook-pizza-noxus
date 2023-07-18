package com.teclinecg.noxus.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pizza_flavor")
public class FlavorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flavor;
    private Double price;

    @ManyToMany(mappedBy = "pizzaFlavors")
    private List<PizzaModel> pizzas;

    public FlavorModel() {
    }

    public FlavorModel(Long id, String flavor, Double price) {
        this.id = id;
        this.flavor = flavor;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlavorModel that = (FlavorModel) o;
        return id.equals(that.id) && Objects.equals(flavor, that.flavor) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flavor, price);
    }
}
