package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.OrderDrink;
import com.teclinecg.noxus.models.OrderDrinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDrinkRepository extends JpaRepository<OrderDrink, OrderDrinkId> {
//    @Modifying
//    @Query("SELECT od FROM OrderDrink od WHERE od.id.order = :orderId")
//    List<Optional<OrderDrink>> findByOrderId(@Param("orderId") Long orderId);

//    @Modifying
//    @Query("SELECT od FROM OrderDrink od WHERE od.id.order = :orderId")
//    void deleteByOrderId(@Param("orderId") Long orderId);
    List<Optional<OrderDrink>> findByIdOrderId(Long orderId);
    void deleteByIdOrderId(Long orderId);

    boolean existsByIdOrderId(Long orderId);
}
