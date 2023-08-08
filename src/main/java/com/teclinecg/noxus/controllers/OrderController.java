package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.OrderDto;
import com.teclinecg.noxus.services.OrderService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @ApiOperation(value = "http://localhost:8080/order?page=0&size=2", notes = "Returns Orders Paginated")
    public ResponseEntity<Page<OrderDto>> findAllOrdersPaginated(Pageable pageable) {
        Page<OrderDto> orders = orderService.findAllOrdersPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(orders);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/order/{orderId}", notes = "Returns order Selected By Id")
    public ResponseEntity<OrderDto> findOrderById(@PathVariable Long id) {
        OrderDto order = orderService.findOrderById(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/order", notes = "Save a new order")
    public ResponseEntity<OrderDto> saveOrder(@Valid @RequestBody OrderDto orderDto) {
        orderDto = orderService.saveOrder(orderDto);
        return ResponseEntity.ok().body(orderDto);
    }

    @PutMapping
    @ApiOperation(value = "http://localhost:8080/order/{orderId}", notes = "Update an existing order")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable Long id, @Valid @RequestBody OrderDto orderDto) {
        orderDto = orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok().body(orderDto);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/order/{orderId}", notes = "Delete an existing order")
    public ResponseEntity<OrderDto> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
