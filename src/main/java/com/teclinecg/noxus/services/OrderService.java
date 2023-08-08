package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.OrderDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.OrderModel;
import com.teclinecg.noxus.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderDto findOrderById(Long id) {
        Optional<OrderModel> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            return new OrderDto(orderOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + id);
        }
    }

    public Page<OrderDto> findAllOrdersPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<OrderModel> pagedOrders = orderRepository.findAll(pageable);

        return pagedOrders.map(OrderDto::new);
    }

    public OrderDto saveOrder(OrderDto orderDto) {
        OrderModel orderModel = new OrderModel(orderDto);
        return new OrderDto(orderRepository.save(orderModel));
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Optional<OrderModel> existentOrderModelOptional = orderRepository.findById(id);

        if (existentOrderModelOptional.isPresent()) {
            OrderModel updatedOrderModel = new OrderModel(orderDto);
            BeanUtils.copyProperties(existentOrderModelOptional, updatedOrderModel);
            return new OrderDto(orderRepository.save(updatedOrderModel));
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
