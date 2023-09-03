package com.teclinecg.noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teclinecg.noxus.dtos.post.CreditCardPostDto;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "credit_card")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class CreditCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;
    private String number;
    private String ccv;
    private String expirationDate;
    private String ownerCPF;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_account_id", nullable = false)
    private CustomerAccountModel customerAccount;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public CreditCardModel() {
    }

    public CreditCardModel(CreditCardPostDto creditCardPostDto) {
        BeanUtils.copyProperties(creditCardPostDto, this);
    }

    public CreditCardModel(Long id, String ownerName, String number, String ccv, String expirationDate, String ownerCPF, CustomerAccountModel customerAccount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.ownerName = ownerName;
        this.number = number;
        this.ccv = ccv;
        this.expirationDate = expirationDate;
        this.ownerCPF = ownerCPF;
        this.customerAccount = customerAccount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getOwnerCPF() {
        return ownerCPF;
    }

    public void setOwnerCPF(String ownerCPF) {
        this.ownerCPF = ownerCPF;
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
        CreditCardModel that = (CreditCardModel) o;
        return id.equals(that.id) && Objects.equals(ownerName, that.ownerName) && Objects.equals(number, that.number) && Objects.equals(ccv, that.ccv) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(ownerCPF, that.ownerCPF) && Objects.equals(customerAccount, that.customerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerName, number, ccv, expirationDate, ownerCPF, customerAccount);
    }
}
