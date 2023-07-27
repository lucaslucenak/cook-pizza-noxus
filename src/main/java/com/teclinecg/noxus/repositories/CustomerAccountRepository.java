package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.CustomerAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccountModel, Long> {
}
