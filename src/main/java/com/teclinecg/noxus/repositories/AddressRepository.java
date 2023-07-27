package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {
}
