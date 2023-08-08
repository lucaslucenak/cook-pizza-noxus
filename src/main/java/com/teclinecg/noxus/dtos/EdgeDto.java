package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.EdgeModel;
import com.teclinecg.noxus.models.PizzaModel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class EdgeDto {

    private Long id;
    @NotNull(message = "Field edge shouldn't be null")
    @NotEmpty(message = "Field edge shouldn't be empty")
    @NotBlank(message = "Field edge shouldn't be blank")
    private String edge;
    @NotNull(message = "Field price shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;
    private List<PizzaModel> pizzas;

    public EdgeDto() {
    }

    public EdgeDto(EdgeModel edgeModel) {
        BeanUtils.copyProperties(edgeModel, this);
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
        EdgeDto that = (EdgeDto) o;
        return id.equals(that.id) && Objects.equals(edge, that.edge) && Objects.equals(price, that.price) && Objects.equals(pizzas, that.pizzas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edge, price, pizzas);
    }
}
