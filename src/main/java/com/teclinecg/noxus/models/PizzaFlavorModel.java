package com.teclinecg.noxus.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizza_flavor")
public class PizzaFlavorModel {

    @EmbeddedId
    private PizzaFlavorId id = new PizzaFlavorId();

    public PizzaFlavorModel(PizzaFlavorId id) {
        this.id = id;
    }

    public PizzaFlavorModel() {
    }

    public PizzaFlavorId getId() {
        return id;
    }

    public void setId(PizzaFlavorId id) {
        this.id = id;
    }
}
