package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.AddressModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class AddressPostDto {

    private Long id;
    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private String streetName;
    @NotNull(message = "Field streetNumber shouldn't be null")
    @NotEmpty(message = "Field streetNumber shouldn't be empty")
    @NotBlank(message = "Field streetNumber shouldn't be blank")
    private String streetNumber;
    @NotNull(message = "Field neighbourhood shouldn't be null")
    private Long neighbourhood;
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
//    @NotEmpty(message = "Field customerAccount shouldn't be empty")
    private Long customerAccount;

    public AddressPostDto() {
    }

    public AddressPostDto(AddressModel addressModel) {
        BeanUtils.copyProperties(addressModel, this);
    }

    public AddressPostDto(Long id, String streetName, String streetNumber, Long neighbourhood, String city, String cep, String complement, String referencePoint, Long customerAccount) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.cep = cep;
        this.complement = complement;
        this.referencePoint = referencePoint;
        this.customerAccount = customerAccount;
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

    public Long getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(Long neighbourhood) {
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

    public Long getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(Long customerAccount) {
        this.customerAccount = customerAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressPostDto that = (AddressPostDto) o;
        return id.equals(that.id) && Objects.equals(streetName, that.streetName) && Objects.equals(streetNumber, that.streetNumber) && Objects.equals(neighbourhood, that.neighbourhood) && Objects.equals(city, that.city) && Objects.equals(cep, that.cep) && Objects.equals(complement, that.complement) && Objects.equals(referencePoint, that.referencePoint) && Objects.equals(customerAccount, that.customerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, streetNumber, neighbourhood, city, cep, complement, referencePoint, customerAccount);
    }
}
