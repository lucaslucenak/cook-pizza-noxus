package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.OrderDrink;
import com.teclinecg.noxus.models.OrderDrinkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDrinkRepository extends JpaRepository<OrderDrink, OrderDrinkId> {
}
