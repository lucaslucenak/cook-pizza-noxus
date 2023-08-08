package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.SizeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeModel, Long> {
}
