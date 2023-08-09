package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.DeliveryTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryTypeModel, Long> {
}
