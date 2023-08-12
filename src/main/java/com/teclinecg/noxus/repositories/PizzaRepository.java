package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.PizzaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaModel, Long> {

    List<Optional<PizzaModel>> findByOrderId(Long orderId);
}
