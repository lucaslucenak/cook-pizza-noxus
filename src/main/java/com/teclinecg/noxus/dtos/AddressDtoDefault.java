package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class AddressDtoDefault {

    private Long id;
    private String streetName;
    private String streetNumber;
    private String neighbourhood;
    private String city;
    private String cep;
    private String complement;
    private String referencePoint;
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