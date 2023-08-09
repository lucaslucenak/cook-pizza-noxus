package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class CreditCardDto {

    private Long id;
    private String ownerName;
    private String number;
    private String ccv;
    private String expirationDate;
    private String ownerCPF;
    private CustomerAccountModel customerAccount;

    public CreditCardDto() {
    }

    public CreditCardDto(CreditCardModel creditCardModel) {
        BeanUtils.copyProperties(creditCardModel, this);
    }

    public CreditCardDto(Long id, String ownerName, String number, String ccv, String expirationDate, String ownerCPF, CustomerAccountModel customerAccount) {
        this.id = id;
        this.ownerName = ownerName;
        this.number = number;
        this.ccv = ccv;
        this.expirationDate = expirationDate;
        this.ownerCPF = ownerCPF;
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

    public String getOwnerCPF() {
        return ownerCPF;
    }

    public void setOwnerCPF(String ownerCPF) {
        this.ownerCPF = ownerCPF;
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
        CreditCardDto that = (CreditCardDto) o;
        return id.equals(that.id) && Objects.equals(ownerName, that.ownerName) && Objects.equals(number, that.number) && Objects.equals(ccv, that.ccv) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(ownerCPF, that.ownerCPF) && Objects.equals(customerAccount, that.customerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerName, number, ccv, expirationDate, ownerCPF, customerAccount);
    }
}
