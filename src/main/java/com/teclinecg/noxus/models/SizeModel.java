package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.PizzaSizeEnum;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.Objects;

@Entity
@Builder
@Table(name = "size")
public class SizeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PizzaSizeEnum size;

    public SizeModel() {
    }

    public SizeModel(PizzaSizeEnum size) {
        this.size = size;
    }

    public SizeModel(Long id, PizzaSizeEnum size) {
        this.id = id;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PizzaSizeEnum getPizzaSize() {
        return size;
    }

    public void setPizzaSize(PizzaSizeEnum pizzaSize) {
        this.size = pizzaSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeModel sizeModel = (SizeModel) o;
        return id.equals(sizeModel.id) && size == sizeModel.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size);
    }
}
