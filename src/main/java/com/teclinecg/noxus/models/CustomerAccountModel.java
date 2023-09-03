package com.teclinecg.noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teclinecg.noxus.dtos.CustomerAccountDto;
import com.teclinecg.noxus.dtos.post.CustomerAccountPostDto;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Table(name = "customer_account")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class CustomerAccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String cellphoneNumber;

    @OneToMany(mappedBy = "customerAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AddressModel> addresses;

    @JsonIgnore
    @OneToMany(mappedBy = "customerAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CreditCardModel> creditCards;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public CustomerAccountModel() {
    }

    public CustomerAccountModel(CustomerAccountPostDto customerAccountDto) {
        BeanUtils.copyProperties(customerAccountDto, this);
    }

    public CustomerAccountModel(CustomerAccountDto customerAccountDto) {
        BeanUtils.copyProperties(customerAccountDto, this);
    }

    public CustomerAccountModel(Long id, String firstName, String lastName, String cpf, String email, String cellphoneNumber, List<AddressModel> addresses, List<CreditCardModel> creditCards, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.addresses = addresses;
        this.creditCards = creditCards;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
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
        CustomerAccountModel that = (CustomerAccountModel) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(cpf, that.cpf) && Objects.equals(email, that.email) && Objects.equals(cellphoneNumber, that.cellphoneNumber) && Objects.equals(addresses, that.addresses) && Objects.equals(creditCards, that.creditCards) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, cpf, email, cellphoneNumber, addresses, creditCards, status);
    }
}
