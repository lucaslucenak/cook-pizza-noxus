package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusModel, Long> {
}
