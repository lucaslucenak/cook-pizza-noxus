package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.OrderDrinkModel;
import com.teclinecg.noxus.models.OrderDrinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDrinkRepository extends JpaRepository<OrderDrinkModel, OrderDrinkId> {
//    @Modifying
//    @Query("SELECT od FROM OrderDrink od WHERE od.id.order = :orderId")
//    List<Optional<OrderDrink>> findByOrderId(@Param("orderId") Long orderId);

//    @Modifying
//    @Query("SELECT od FROM OrderDrink od WHERE od.id.order = :orderId")
//    void deleteByOrderId(@Param("orderId") Long orderId);
    List<Optional<OrderDrinkModel>> findByIdOrderId(Long orderId);
    void deleteByIdOrderId(Long orderId);

    boolean existsByIdOrderId(Long orderId);
}
