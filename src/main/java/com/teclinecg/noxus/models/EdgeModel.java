package com.teclinecg.noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teclinecg.noxus.dtos.EdgeDto;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Table(name = "edge")
public class EdgeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String edge;
    private Double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "edges")
    private List<PizzaModel> pizzas;

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
