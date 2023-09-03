package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.OrderDrinkModel;
import com.teclinecg.noxus.models.OrderPizzaId;
import com.teclinecg.noxus.models.OrderPizzaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderPizzaRepository extends JpaRepository<OrderPizzaModel, OrderPizzaId> {
    List<Optional<OrderPizzaModel>> findByIdOrderId(Long orderId);
    void deleteByIdOrderId(Long orderId);

    boolean existsByIdOrderId(Long orderId);
}
