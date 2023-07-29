package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.OrderDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageQuantityException;
import com.teclinecg.noxus.exceptions.InvalidRegisterQuantityException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.OrderModel;
import com.teclinecg.noxus.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDtoDefault findOrderById(Long id) {
        Optional<OrderModel> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            return new OrderDtoDefault(orderOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + id);
        }
    }

    public List<OrderDtoDefault> findAllOrdersPaginated(Integer pagQtt, Integer registerQtt) {
        if (pagQtt < 1) {
            throw new InvalidPageQuantityException("Invalid Page Quantity. Must be greater than one");
        }
        if (registerQtt < 1) {
            throw new InvalidRegisterQuantityException("Invalid Register Quantity. Must be greater than one");
        }

        // Paginated JPA query
        Pageable pageRequest = PageRequest.of(pagQtt, registerQtt);
        Page<OrderModel> result = orderRepository.findAll(pageRequest);

        // Converting Models to DTOs
        List<OrderModel> orderModels = result.stream().toList();
        List<OrderDtoDefault> dtos = new ArrayList<>();

        for (OrderModel i : orderModels) {
            dtos.add(new OrderDtoDefault(i));
        }

        return dtos;
    }

    public OrderDtoDefault saveOrder(@Valid OrderDtoDefault orderDto) {
        OrderModel orderModel = new OrderModel(orderDto);
        return new OrderDtoDefault(orderRepository.save(orderModel));
    }

    public OrderDtoDefault updateOrder(Long id, @Valid OrderDtoDefault orderDto) {
        Optional<OrderModel> existentOrderModelOptional = orderRepository.findById(id);

        if (existentOrderModelOptional.isPresent()) {
            OrderModel updatedOrderModel = new OrderModel(orderDto);
            BeanUtils.copyProperties(existentOrderModelOptional, updatedOrderModel);
            return new OrderDtoDefault(orderRepository.save(updatedOrderModel));
        } else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + id);
        }
    }

    public void deleteOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + id);
        }
    }
}
