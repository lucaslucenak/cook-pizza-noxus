package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.PizzaSizeEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "size")
public class SizeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PizzaSizeEnum pizzaSize;

    public SizeModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PizzaSizeEnum getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(PizzaSizeEnum pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeModel sizeModel = (SizeModel) o;
        return id.equals(sizeModel.id) && pizzaSize == sizeModel.pizzaSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pizzaSize);
    }
}
