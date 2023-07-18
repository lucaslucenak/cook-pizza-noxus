package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.DeliveryTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "delivery_yype")
public class DeliveryTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryTypeEnum deliveryType;
}
