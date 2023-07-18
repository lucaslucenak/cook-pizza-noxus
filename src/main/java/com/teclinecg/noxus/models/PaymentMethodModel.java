package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.PaymentMethodEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "payment_method")
public class PaymentMethodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethodEnum paymentMethod;

    public PaymentMethodModel() {
    }

    public PaymentMethodModel(Long id, PaymentMethodEnum paymentMethod) {
        this.id = id;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethodModel that = (PaymentMethodModel) o;
        return id.equals(that.id) && paymentMethod == that.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod);
    }
}
