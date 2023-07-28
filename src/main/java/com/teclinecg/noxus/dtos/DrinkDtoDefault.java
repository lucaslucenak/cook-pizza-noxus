package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.DrinkModel;
import com.teclinecg.noxus.models.OrderModel;
import jakarta.persistence.ManyToMany;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class DrinkDtoDefault {

    private Long id;
    private String name;
    private Double price;
    private List<OrderModel> orders;

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

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkDtoDefault that = (DrinkDtoDefault) o;
        return id.equals(that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, orders);
    }
}
