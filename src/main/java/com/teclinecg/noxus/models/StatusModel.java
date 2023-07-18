package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.StatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class StatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;
}
