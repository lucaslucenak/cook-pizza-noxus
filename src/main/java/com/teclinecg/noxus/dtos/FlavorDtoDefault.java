package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.FlavorModel;
import com.teclinecg.noxus.models.PizzaModel;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class FlavorDtoDefault {

    private Long id;
    @NotNull(message = "Field flavor shouldn't be null")
    @NotEmpty(message = "Field flavor shouldn't be empty")
    @NotBlank(message = "Field flavor shouldn't be blank")
    private String flavor;
    @NotNull(message = "Field price shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;
    private List<PizzaModel> pizzas;

    public FlavorDtoDefault() {
    }

    public FlavorDtoDefault(FlavorModel flavorModel) {
        BeanUtils.copyProperties(flavorModel, this);
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
        FlavorDtoDefault that = (FlavorDtoDefault) o;
        return id.equals(that.id) && Objects.equals(flavor, that.flavor) && Objects.equals(price, that.price) && Objects.equals(pizzas, that.pizzas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flavor, price, pizzas);
    }
}
