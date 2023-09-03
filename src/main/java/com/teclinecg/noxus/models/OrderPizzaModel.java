package com.teclinecg.noxus.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_pizza")
public class OrderPizzaModel {

    @EmbeddedId
    private OrderPizzaId id = new OrderPizzaId();

    private Integer quantity;

    public OrderPizzaModel() {
    }

    public OrderPizzaModel(OrderPizzaId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public OrderPizzaId getId() {
        return id;
    }

    public void setId(OrderPizzaId id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
