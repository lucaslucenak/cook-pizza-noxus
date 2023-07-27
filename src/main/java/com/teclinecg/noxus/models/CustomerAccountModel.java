package com.teclinecg.noxus.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer_account")
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

    @OneToMany(mappedBy = "customerAccount", cascade = CascadeType.ALL)
    private List<AddressModel> addresses;

    @OneToMany(mappedBy = "customerAccount", cascade = CascadeType.ALL)
    private List<CreditCardModel> creditCards;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel status;

    public CustomerAccountModel() {
    }

    public CustomerAccountModel(Long id, String firstName, String lastName, String cpf, String email, List<AddressModel> addresses, List<CreditCardModel> creditCards, StatusModel status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
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
        CustomerAccountModel that = (CustomerAccountModel) o;
        return id.equals(that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(cpf, that.cpf) && Objects.equals(email, that.email) && Objects.equals(addresses, that.addresses) && Objects.equals(creditCards, that.creditCards) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, cpf, email, addresses, creditCards, status);
    }
}
