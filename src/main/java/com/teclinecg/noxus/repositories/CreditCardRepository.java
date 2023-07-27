package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.CreditCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardModel, Long> {
}
