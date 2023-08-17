package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderDto {

    private Long id;
    private Double orderPrice;
    private String observation;
    private LocalDateTime dispatchDateTime;
    private LocalDateTime arrivalForecast;
    private List<PizzaModel> pizzas;
    private List<DrinkModel> drinks;
    @NotNull(message = "Field customerAccount shouldn't be null")
    @NotEmpty(message = "Field customerAccount shouldn't be empty")
    private CustomerAccountModel customerAccount;
    @NotNull(message = "Field address shouldn't be null")
    @NotEmpty(message = "Field address shouldn't be empty")
    private AddressModel address;
    @NotNull(message = "Field deliveryTax shouldn't be null")
    @NotEmpty(message = "Field deliveryTax shouldn't be empty")
    private DeliveryTaxModel deliveryTax;
    @NotNull(message = "Field paymentMethod shouldn't be null")
    @NotEmpty(message = "Field paymentMethod shouldn't be empty")
    private PaymentMethodModel paymentMethod;
    @NotNull(message = "Field deliveryType shouldn't be null")
    @NotEmpty(message = "Field deliveryType shouldn't be empty")
    private DeliveryTypeModel deliveryType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderDto() {
    }

    public OrderDto(OrderModel orderModel) {
        BeanUtils.copyProperties(orderModel, this);
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

    public List<PizzaModel> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaModel> pizzas) {
        this.pizzas = pizzas;
    }

    public List<DrinkModel> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkModel> drinks) {
        this.drinks = drinks;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto that = (OrderDto) o;
        return id.equals(that.id) && Objects.equals(orderPrice, that.orderPrice) && Objects.equals(observation, that.observation) && Objects.equals(dispatchDateTime, that.dispatchDateTime) && Objects.equals(arrivalForecast, that.arrivalForecast) && Objects.equals(pizzas, that.pizzas) && Objects.equals(drinks, that.drinks) && Objects.equals(customerAccount, that.customerAccount) && Objects.equals(address, that.address) && Objects.equals(deliveryTax, that.deliveryTax) && Objects.equals(paymentMethod, that.paymentMethod) && Objects.equals(deliveryType, that.deliveryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderPrice, observation, dispatchDateTime, arrivalForecast, pizzas, drinks, customerAccount, address, deliveryTax, paymentMethod, deliveryType);
    }
}
