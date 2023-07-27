package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.AuditModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditModel, Long> {
}
