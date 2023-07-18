package com.teclinecg.noxus.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "edge")
public class EdgeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String edge;
    private Double price;

    @ManyToMany(mappedBy = "edges")
    private List<PizzaModel> pizzas;

    public EdgeModel(Long id, String edge, Double price) {
        this.id = id;
        this.edge = edge;
        this.price = price;
    }

    public EdgeModel() {
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
