package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.PizzaSizeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "size")
public class SizeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PizzaSizeEnum pizzaSize;
}
