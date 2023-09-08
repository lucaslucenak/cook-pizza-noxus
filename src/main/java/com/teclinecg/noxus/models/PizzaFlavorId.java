package com.teclinecg.noxus.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class PizzaFlavorId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private PizzaModel pizza;

    @ManyToOne
    @JoinColumn(name = "flavor_id")
    private FlavorModel flavor;

    public PizzaFlavorId() {
    }

    public PizzaFlavorId(PizzaModel pizza, FlavorModel flavor) {
        this.pizza = pizza;
        this.flavor = flavor;
    }

    public PizzaModel getOrder() {
        return pizza;
    }

    public void setOrder(PizzaModel pizza) {
        this.pizza = pizza;
    }

    public FlavorModel getFlavor() {
        return flavor;
    }

    public void setFlavor(FlavorModel flavor) {
        this.flavor = flavor;
    }
}
