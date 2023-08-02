package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.models.StatusModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class CustomerAccountDtoDefault {

    private Long id;
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull @NotEmpty @NotBlank
    private String lastName;
    @NotNull @NotEmpty @NotBlank
    private String cpf;
    @NotNull @NotEmpty @NotBlank
    private String email;
    @NotNull @NotEmpty @NotBlank
    private String cellphoneNumber;
    private List<AddressModel> addresses;
    private List<CreditCardModel> creditCards;
    @NotNull @NotEmpty @NotBlank
    private StatusModel status;

    public CustomerAccountDtoDefault() {
    }

    public CustomerAccountDtoDefault(CustomerAccountModel customerAccountModel) {
        BeanUtils.copyProperties(customerAccountModel, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public List<AddressModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressModel> addresses) {
        this.addresses = addresses;
    }

    public List<CreditCardModel> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardModel> creditCards) {
        this.creditCards = creditCards;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAccountDtoDefault that = (CustomerAccountDtoDefault) o;
        return id.equals(that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(cpf, that.cpf) && Objects.equals(email, that.email) && Objects.equals(addresses, that.addresses) && Objects.equals(creditCards, that.creditCards) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, cpf, email, addresses, creditCards, status);
    }
}
