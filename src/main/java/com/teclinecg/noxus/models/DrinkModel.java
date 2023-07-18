package com.teclinecg.noxus.models;

import jakarta.persistence.*;

import java.util.List;

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
}
