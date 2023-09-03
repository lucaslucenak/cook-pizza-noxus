package com.teclinecg.noxus.dtos.post;

import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.models.StatusModel;
import com.teclinecg.noxus.validators.CellphoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class CustomerAccountPostDto {

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
    @CellphoneNumber
    private String cellphoneNumber;
    private List<AddressModel> addresses;
    private List<CreditCardModel> creditCards;
//    @NotNull(message = "Field status shouldn't be null")
    private Long status;

    public CustomerAccountPostDto() {
    }

    public CustomerAccountPostDto(CustomerAccountModel customerAccountModel) {
        BeanUtils.copyProperties(customerAccountModel, this);
    }

    public CustomerAccountPostDto(Long id, String firstName, String lastName, String cpf, String email, String cellphoneNumber, List<AddressModel> addresses, List<CreditCardModel> creditCards, Long status) {
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
