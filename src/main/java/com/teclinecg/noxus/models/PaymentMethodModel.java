package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.PaymentMethodEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "payment_method")
public class PaymentMethodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethodEnum paymentMethod;
}
