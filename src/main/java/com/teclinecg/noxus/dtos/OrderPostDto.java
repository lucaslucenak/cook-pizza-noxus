package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class OrderPostDto {

    private Long id;
    private Double orderPrice;
    private String observation;
    private LocalDateTime dispatchDateTime;
    private LocalDateTime arrivalForecast;
    private List<PizzaPostDto> pizzas;
    private List<Long> drinks;
    private Long customerAccount;
    private Long address;
    private Long deliveryTax;
    private Long paymentMethod;
    private Long deliveryType;

    public OrderPostDto() {
    }

    public OrderPostDto(OrderModel orderModel) {
        BeanUtils.copyProperties(orderModel, this);
    }

    public OrderPostDto(Long id, Double orderPrice, String observation, LocalDateTime dispatchDateTime, LocalDateTime arrivalForecast, List<PizzaPostDto> pizzas, List<Long> drinks, Long customerAccount, Long address, Long deliveryTax, Long paymentMethod, Long deliveryType) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.observation = observation;
        this.dispatchDateTime = dispatchDateTime;
        this.arrivalForecast = arrivalForecast;
        this.pizzas = pizzas;
        this.drinks = drinks;
        this.customerAccount = customerAccount;
        this.address = address;
        this.deliveryTax = deliveryTax;
        this.paymentMethod = paymentMethod;
        this.deliveryType = deliveryType;
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

    public List<PizzaPostDto> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaPostDto> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Long> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Long> drinks) {
        this.drinks = drinks;
    }

    public Long getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Long customerAccount) {
        this.customerAccount = customerAccount;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public Long getDeliveryTax() {
        return deliveryTax;
    }

    public void setDeliveryTax(Long deliveryTax) {
        this.deliveryTax = deliveryTax;
    }

    public Long getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Long paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Long deliveryType) {
        this.deliveryType = deliveryType;
    }
}
