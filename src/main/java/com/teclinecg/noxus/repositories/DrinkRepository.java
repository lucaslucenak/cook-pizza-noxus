package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.DrinkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkModel, Long> {
}
