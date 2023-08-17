package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.models.StatusModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerAccountDto {


    private Long id;
    @NotNull(message = "Field firstName shouldn't be null")
    @NotEmpty(message = "Field firstName shouldn't be empty")
    @NotBlank(message = "Field firstName shouldn't be blank")
    private String firstName;
    @NotNull(message = "Field lastName shouldn't be null")
    @NotEmpty(message = "Field lastName shouldn't be empty")
    @NotBlank(message = "Field lastName shouldn't be blank")
    private String lastName;
    @NotNull(message = "Field cpf shouldn't be null")
    @NotEmpty(message = "Field cpf shouldn't be empty")
    @NotBlank(message = "Field cpf shouldn't be blank")
    @CPF
    private String cpf;
    @NotNull(message = "Field email shouldn't be null")
    @NotEmpty(message = "Field email shouldn't be empty")
    @NotBlank(message = "Field email shouldn't be blank")
    @Email
    private String email;
    @NotNull(message = "Field cellphoneNumber shouldn't be null")
    @NotEmpty(message = "Field cellphoneNumber shouldn't be empty")
    @NotBlank(message = "Field cellphoneNumber shouldn't be blank")
    private String cellphoneNumber;
    private List<AddressModel> addresses;
    private List<CreditCardModel> creditCards;
    @NotNull(message = "Field status shouldn't be null")
    @NotEmpty(message = "Field status shouldn't be empty")
    private StatusModel status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerAccountDto() {
    }

    public CustomerAccountDto(CustomerAccountModel customerAccountModel) {
        BeanUtils.copyProperties(customerAccountModel, this);
    }

    public CustomerAccountDto(Long id, String firstName, String lastName, String cpf, String email, String cellphoneNumber, List<AddressModel> addresses, List<CreditCardModel> creditCards, StatusModel status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.addresses = addresses;
        this.creditCards = creditCards;
        this.status = status;
    }

    public CustomerAccountDto(Long id, String firstName, String lastName, String cpf, String email, String cellphoneNumber, List<AddressModel> addresses, List<CreditCardModel> creditCards, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
}
