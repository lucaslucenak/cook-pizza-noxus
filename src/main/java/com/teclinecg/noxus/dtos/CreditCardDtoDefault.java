package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class CreditCardDtoDefault {

    private Long id;
    @NotNull(message = "Field ownerName shouldn't be null")
    @NotEmpty(message = "Field ownerName shouldn't be empty")
    @NotBlank(message = "Field ownerName shouldn't be blank")
    private String ownerName;
    @NotNull(message = "Field number shouldn't be null")
    @NotEmpty(message = "Field number shouldn't be empty")
    @NotBlank(message = "Field number shouldn't be blank")
    private String number;
    @NotNull(message = "Field ccv shouldn't be null")
    @NotEmpty(message = "Field ccv shouldn't be empty")
    @NotBlank(message = "Field ccv shouldn't be blank")
    @Min(value = 3)
    @Max(value = 4)
    private String ccv;
    @NotNull(message = "Field expirationDate shouldn't be null")
    @NotEmpty(message = "Field expirationDate shouldn't be empty")
    @NotBlank(message = "Field expirationDate shouldn't be blank")
    private String expirationDate;
    @NotNull(message = "Field ownerCPF shouldn't be null")
    @NotEmpty(message = "Field ownerCPF shouldn't be empty")
    @NotBlank(message = "Field ownerCPF shouldn't be blank")
    @CPF
    private String ownerCPF;
    @NotNull(message = "Field customerAccount shouldn't be null")
    @NotEmpty(message = "Field customerAccount shouldn't be empty")
    @NotBlank(message = "Field customerAccount shouldn't be blank")
    private CustomerAccountModel customerAccount;

    public CreditCardDtoDefault() {
    }

    public CreditCardDtoDefault(CreditCardModel creditCardModel) {
        BeanUtils.copyProperties(creditCardModel, this);
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
        CreditCardDtoDefault that = (CreditCardDtoDefault) o;
        return id.equals(that.id) && Objects.equals(ownerName, that.ownerName) && Objects.equals(number, that.number) && Objects.equals(ccv, that.ccv) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(ownerCPF, that.ownerCPF) && Objects.equals(customerAccount, that.customerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerName, number, ccv, expirationDate, ownerCPF, customerAccount);
    }
}
