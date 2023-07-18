package com.teclinecg.noxus.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "pizza")
public class PizzaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pizza_size_id")
    private SizeModel pizzaSize;

    @ManyToMany
    @JoinTable(
            name = "pizza_flavor",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "flavor_id")
    )
    private List<FlavorModel> flavors;
}
