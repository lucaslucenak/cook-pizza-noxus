package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.DrinkModel;
import com.teclinecg.noxus.models.OrderModel;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class DrinkDtoDefault {

    private Long id;
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;
    @NotNull
    @NotEmpty
    @NotBlank
    private Double price;

    public DrinkDtoDefault() {
    }

    public DrinkDtoDefault(DrinkModel drinkModel) {
        BeanUtils.copyProperties(drinkModel, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        DrinkDtoDefault that = (DrinkDtoDefault) o;
        return id.equals(that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
