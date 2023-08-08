package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class AddressDtoDefault {

    private Long id;
    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    @Max(value = 100)
    private String streetName;
    @NotNull(message = "Field streetNumber shouldn't be null")
    @NotEmpty(message = "Field streetNumber shouldn't be empty")
    @NotBlank(message = "Field streetNumber shouldn't be blank")
    private String streetNumber;
    @NotNull(message = "Field neighbourhood shouldn't be null")
    @NotEmpty(message = "Field neighbourhood shouldn't be empty")
    @NotBlank(message = "Field neighbourhood shouldn't be blank")
    private String neighbourhood;
    @NotNull(message = "Field city shouldn't be null")
    @NotEmpty(message = "Field city shouldn't be empty")
    @NotBlank(message = "Field city shouldn't be blank")
    private String city;
    @NotNull(message = "Field cep shouldn't be null")
    @NotEmpty(message = "Field cep shouldn't be empty")
    @NotBlank(message = "Field cep shouldn't be blank")
    private String cep;
    private String complement;
    @NotNull(message = "Field referencePoint shouldn't be null")
    @NotEmpty(message = "Field referencePoint shouldn't be empty")
    @NotBlank(message = "Field referencePoint shouldn't be blank")
    private String referencePoint;
    @NotNull(message = "Field customerAccount shouldn't be null")
    @NotEmpty(message = "Field customerAccount shouldn't be empty")
    private CustomerAccountModel customerAccount;

    public AddressDtoDefault() {
    }

    public AddressDtoDefault(AddressModel addressModel) {
        BeanUtils.copyProperties(addressModel, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
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
        AddressDtoDefault that = (AddressDtoDefault) o;
        return id.equals(that.id) && Objects.equals(streetName, that.streetName) && Objects.equals(streetNumber, that.streetNumber) && Objects.equals(neighbourhood, that.neighbourhood) && Objects.equals(city, that.city) && Objects.equals(cep, that.cep) && Objects.equals(complement, that.complement) && Objects.equals(referencePoint, that.referencePoint) && Objects.equals(customerAccount, that.customerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, streetNumber, neighbourhood, city, cep, complement, referencePoint, customerAccount);
    }
}
