package com.teclinecg.noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teclinecg.noxus.dtos.OrderDto;
import com.teclinecg.noxus.dtos.post.OrderPostDto;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Builder
@Table(name = "orderrr")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double orderPrice;
    private String observation;
    private LocalDateTime dispatchDateTime;
    private LocalDateTime arrivalForecast;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    @JoinTable(name = "order_pizza",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "pizza_id")
//    )
//    private List<PizzaModel> pizzas = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonIgnore
//    @JoinTable(
//            name = "order_drink",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "drink_id")
//    )
//    @ElementCollection
//    @CollectionTable(name = "order_drink", joinColumns = @JoinColumn(name = "order_id"))
//    @MapKeyJoinColumn(name = "drink_id")
//    @Column(name = "quantity")
//    private Map<DrinkModel, Long> drinks = new HashMap<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_account_id", nullable = false)
    private CustomerAccountModel customerAccount;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "address_id", nullable = false)
    private AddressModel address;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "delivery_tax_id", nullable = false)
    private DeliveryTaxModel deliveryTax;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethodModel paymentMethod;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "delivery_type_id", nullable = false)
    private DeliveryTypeModel deliveryType;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public OrderModel() {
    }

    public OrderModel(OrderDto orderDto) {
        BeanUtils.copyProperties(orderDto, this);
    }

    public OrderModel(OrderPostDto orderPostDto) {
        BeanUtils.copyProperties(orderPostDto, this);
    }

    public OrderModel(Long id, Double orderPrice, String observation, LocalDateTime dispatchDateTime, LocalDateTime arrivalForecast, CustomerAccountModel customerAccount, AddressModel address, DeliveryTaxModel deliveryTax, PaymentMethodModel paymentMethod, DeliveryTypeModel deliveryType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.observation = observation;
        this.dispatchDateTime = dispatchDateTime;
        this.arrivalForecast = arrivalForecast;
        this.customerAccount = customerAccount;
        this.address = address;
        this.deliveryTax = deliveryTax;
        this.paymentMethod = paymentMethod;
        this.deliveryType = deliveryType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public LocalDateTime getDispatchDateTime() {
        return dispatchDateTime;
    }

    public void setDispatchDateTime(LocalDateTime dispatchDateTime) {
        this.dispatchDateTime = dispatchDateTime;
    }

    public LocalDateTime getArrivalForecast() {
        return arrivalForecast;
    }

    public void setArrivalForecast(LocalDateTime arrivalForecast) {
        this.arrivalForecast = arrivalForecast;
    }

    public CustomerAccountModel getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccountModel customerAccount) {
        this.customerAccount = customerAccount;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public DeliveryTaxModel getDeliveryTax() {
        return deliveryTax;
    }

    public void setDeliveryTax(DeliveryTaxModel deliveryTax) {
        this.deliveryTax = deliveryTax;
    }

    public PaymentMethodModel getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodModel paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DeliveryTypeModel getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryTypeModel deliveryType) {
        this.deliveryType = deliveryType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", orderPrice=" + orderPrice +
                ", observation='" + observation + '\'' +
                ", dispatchDateTime=" + dispatchDateTime +
                ", arrivalForecast=" + arrivalForecast +
                ", customerAccount=" + customerAccount +
                ", address=" + address +
                ", deliveryTax=" + deliveryTax +
                ", paymentMethod=" + paymentMethod +
                ", deliveryType=" + deliveryType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return id.equals(that.id) && Objects.equals(orderPrice, that.orderPrice) && Objects.equals(observation, that.observation) && Objects.equals(dispatchDateTime, that.dispatchDateTime) && Objects.equals(arrivalForecast, that.arrivalForecast) && Objects.equals(customerAccount, that.customerAccount) && Objects.equals(address, that.address) && Objects.equals(deliveryTax, that.deliveryTax) && Objects.equals(paymentMethod, that.paymentMethod) && Objects.equals(deliveryType, that.deliveryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderPrice, observation, dispatchDateTime, arrivalForecast, customerAccount, address, deliveryTax, paymentMethod, deliveryType);
    }
}
