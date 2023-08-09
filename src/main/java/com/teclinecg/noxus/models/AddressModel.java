package com.teclinecg.noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teclinecg.noxus.dtos.AddressPostDto;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "address")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String streetNumber;

    @Column(nullable = false)
    private String neighbourhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String referencePoint;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_account_id", nullable = false)
    private CustomerAccountModel customerAccount;

    public AddressModel() {
    }

    public AddressModel(AddressPostDto addressDto) {
        BeanUtils.copyProperties(addressDto, this);
    }

    public AddressModel(Long id, String streetName, String streetNumber, String neighbourhood, String city, String cep, String complement, String referencePoint, CustomerAccountModel customerAccount) {
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
        AddressModel that = (AddressModel) o;
        return id.equals(that.id) && Objects.equals(streetName, that.streetName) && Objects.equals(streetNumber, that.streetNumber) && Objects.equals(neighbourhood, that.neighbourhood) && Objects.equals(city, that.city) && Objects.equals(cep, that.cep) && Objects.equals(complement, that.complement) && Objects.equals(referencePoint, that.referencePoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, streetNumber, neighbourhood, city, cep, complement, referencePoint);
    }
}
