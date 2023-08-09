package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.PaymentMethodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodModel, Long> {
}
