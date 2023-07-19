package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.DeliveryTypeEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "delivery_type")
public class DeliveryTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryTypeEnum deliveryType;

    public DeliveryTypeModel() {
    }

    public DeliveryTypeModel(Long id, DeliveryTypeEnum deliveryType) {
        this.id = id;
        this.deliveryType = deliveryType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryTypeEnum getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryTypeEnum deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryTypeModel that = (DeliveryTypeModel) o;
        return id.equals(that.id) && deliveryType == that.deliveryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deliveryType);
    }
}
