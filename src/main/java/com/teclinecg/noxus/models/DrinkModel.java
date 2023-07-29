package com.teclinecg.noxus.models;

import com.teclinecg.noxus.dtos.DrinkDtoDefault;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "drink")
public class DrinkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @ManyToMany(mappedBy = "drinks")
    private List<OrderModel> orders;

    public DrinkModel() {
    }

    public DrinkModel(DrinkDtoDefault drinkDto) {
        BeanUtils.copyProperties(drinkDto, this);
    }

    public DrinkModel(Long id, String name, Double price, List<OrderModel> orders) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orders = orders;
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
        DrinkModel that = (DrinkModel) o;
        return id.equals(that.id) && Objects.equals(name, that.name) && Objects.equals(price, that.price) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, orders);
    }
}
