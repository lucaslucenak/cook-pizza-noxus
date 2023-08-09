package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.PizzaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaModel, Long> {

    List<PizzaModel> findByOrderId(Long orderId);
}
