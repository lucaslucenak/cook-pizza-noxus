package com.teclinecg.noxus.services;

import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.OrderDrinkModel;
import com.teclinecg.noxus.models.OrderPizzaModel;
import com.teclinecg.noxus.repositories.OrderPizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderPizzaService {

    @Autowired
    private OrderPizzaRepository orderPizzaRepository;

    public List<OrderPizzaModel> findOrderPizzasByOrderId(Long orderId) {
        List<Optional<OrderPizzaModel>> orderPizzas = orderPizzaRepository.findByIdOrderId(orderId);
        List<OrderPizzaModel> orderPizzasReturnModels = new ArrayList<>();

        for (Optional<OrderPizzaModel> i : orderPizzas) {
            if (i.isPresent()) {
                orderPizzasReturnModels.add(i.get());
            } else {
                throw new ResourceNotFoundException("Resource: OrderPizza. Not found with order id: " + orderId);
            }
        }
        return orderPizzasReturnModels;
    }

    public void deleteOrderPizzasByOrderId(Long orderId) {
        if (orderPizzaRepository.findByIdOrderId(orderId).size() > 0) {
            orderPizzaRepository.deleteByIdOrderId(orderId);
        }
        else {
            throw new ResourceNotFoundException("Resource: OrderPizza. Not found with order id: " + orderId);
        }
    }

    public OrderPizzaModel saveOrderPizza(OrderPizzaModel orderPizzaModel) {
        return orderPizzaRepository.save(orderPizzaModel);
    }
}
