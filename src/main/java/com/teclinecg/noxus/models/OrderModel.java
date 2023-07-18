package com.teclinecg.noxus.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orderrr")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double orderPrice;
    private String observation;

    @ManyToMany
    @JoinTable(
            name = "order_pizza",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<PizzaModel> pizzas;

    @ManyToMany
    @JoinTable(
            name = "order_drink",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "drink_id")
    )
    private List<DrinkModel> drinks;

    @ManyToOne
    @JoinColumn(name = "client_account_id")
    private ClientAccountModel clientAccount;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressModel address;

    @ManyToOne
    @JoinColumn(name = "delivery_tax_id")
    private DeliveryTaxModel deliveryTax;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethodModel paymentMethod;

    @ManyToOne
    @JoinColumn(name = "delivery_type_id")
    private DeliveryTypeModel deliveryType;
}
