package com.teclinecg.noxus.models;


import jakarta.persistence.*;
import lombok.Builder;

import java.util.Objects;

@Entity
@Builder
@Table(name = "delivery_tax")
public class DeliveryTaxModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tax;
    private String neighbourhood;

    public DeliveryTaxModel(Long id, Double tax, String neighbourhood) {
        this.id = id;
        this.tax = tax;
        this.neighbourhood = neighbourhood;
    }

    public DeliveryTaxModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryTaxModel that = (DeliveryTaxModel) o;
        return id.equals(that.id) && Objects.equals(tax, that.tax) && Objects.equals(neighbourhood, that.neighbourhood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tax, neighbourhood);
    }
}
