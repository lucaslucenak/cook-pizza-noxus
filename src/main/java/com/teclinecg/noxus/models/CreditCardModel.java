package com.teclinecg.noxus.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "credit_card")
public class CreditCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;
    private String number;
    private String ccv;
    private String expirationDate;

    @ManyToOne
    @JoinColumn(name = "customer_account_id")
    private CustomerAccountModel customerAccount;

    public CreditCardModel() {
    }

    public CreditCardModel(Long id, String ownerName, String number, String ccv, String expirationDate, CustomerAccountModel customerAccount) {
        this.id = id;
        this.ownerName = ownerName;
        this.number = number;
        this.ccv = ccv;
        this.expirationDate = expirationDate;
        this.customerAccount = customerAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public CustomerAccountModel getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccountModel customerAccount) {
        this.customerAccount = customerAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardModel that = (CreditCardModel) o;
        return id.equals(that.id) && Objects.equals(ownerName, that.ownerName) && Objects.equals(number, that.number) && Objects.equals(ccv, that.ccv) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(customerAccount, that.customerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerName, number, ccv, expirationDate, customerAccount);
    }
}
