package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.OrderModel;
import com.teclinecg.noxus.models.PizzaModel;
import com.teclinecg.noxus.models.SizeModel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PizzaDtoDefault {

    @NotNull
    private Long id;

    @NotNull
    private Double price;

    @Valid
    private OrderModel order;

    @Valid
    private SizeModel pizzaSize;

    public PizzaDtoDefault() {
    }

    public PizzaDtoDefault(PizzaModel pizzaModel) {
        BeanUtils.copyProperties(pizzaModel, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public SizeModel getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(SizeModel pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaDtoDefault that = (PizzaDtoDefault) o;
        return id.equals(that.id) && Objects.equals(price, that.price) && Objects.equals(order, that.order) && Objects.equals(pizzaSize, that.pizzaSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, order, pizzaSize);
    }
}
