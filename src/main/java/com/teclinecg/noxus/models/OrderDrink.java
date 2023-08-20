package com.teclinecg.noxus.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_drink")
public class OrderDrink {

    @EmbeddedId
    private OrderDrinkId id = new OrderDrinkId();

    private Integer quantity;

    public OrderDrink() {
    }

    public OrderDrink(OrderDrinkId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public OrderDrinkId getId() {
        return id;
    }

    public void setId(OrderDrinkId id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
