package com.teclinecg.noxus.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class OrderPizzaId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private PizzaModel pizza;

    public OrderPizzaId() {
    }

    public OrderPizzaId(OrderModel order, PizzaModel pizza) {
        this.order = order;
        this.pizza = pizza;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public PizzaModel getPizza() {
        return pizza;
    }

    public void setPizza(PizzaModel pizza) {
        this.pizza = pizza;
    }
}
