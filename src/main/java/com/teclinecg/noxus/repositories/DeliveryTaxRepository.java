package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.DeliveryTaxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryTaxRepository extends JpaRepository<DeliveryTaxModel, Long> {
    Optional<DeliveryTaxModel> findByNeighbourhoodId(Long id);
}
