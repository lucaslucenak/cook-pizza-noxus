package com.teclinecg.noxus.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "client_account")
public class ClientAccountModel {

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

    @OneToMany(mappedBy = "clientAccount", cascade = CascadeType.ALL)
    private List<AddressModel> address;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel status;
}
