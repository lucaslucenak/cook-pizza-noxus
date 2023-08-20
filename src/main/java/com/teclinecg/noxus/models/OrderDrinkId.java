package com.teclinecg.noxus.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class OrderDrinkId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private DrinkModel drink;

    public OrderDrinkId() {
    }

    public OrderDrinkId(OrderModel order, DrinkModel drink) {
        this.order = order;
        this.drink = drink;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public DrinkModel getDrink() {
        return drink;
    }

    public void setDrink(DrinkModel drink) {
        this.drink = drink;
    }
}
