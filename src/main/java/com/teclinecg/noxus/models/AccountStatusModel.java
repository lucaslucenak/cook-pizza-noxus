package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.AccountStatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "account_status")
public class AccountStatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private AccountStatusEnum accountStatus;
}
