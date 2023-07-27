package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.EdgeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeRepository extends JpaRepository<EdgeModel, Long> {
}
