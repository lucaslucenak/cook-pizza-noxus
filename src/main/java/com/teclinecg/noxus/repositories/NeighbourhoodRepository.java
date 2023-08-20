package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.NeighbourhoodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighbourhoodRepository extends JpaRepository<NeighbourhoodModel, Long> {
}
