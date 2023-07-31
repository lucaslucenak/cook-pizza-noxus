package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.OrderDtoDefault;
import com.teclinecg.noxus.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDtoDefault>> findAllOrdersPaginated(
            @RequestParam(value = "pagQtt", defaultValue = "1") Integer pagQtt,
            @RequestParam(value = "registerQtt", defaultValue = "10") Integer registerQtt
    ) {
        List<OrderDtoDefault> orders = orderService.findAllOrdersPaginated(pagQtt, registerQtt);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", pagQtt.toString());
        headers.add("Requested-Register-Qtt", registerQtt.toString());

        return ResponseEntity.ok().headers(headers).body(orders);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDtoDefault> findOrderById(@PathVariable Long id) {
        OrderDtoDefault order = orderService.findOrderById(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<OrderDtoDefault> saveOrder(@Validated @RequestBody OrderDtoDefault orderDto) {
        orderDto = orderService.saveOrder(orderDto);
        return ResponseEntity.ok().body(orderDto);
    }

    @PutMapping
    public ResponseEntity<OrderDtoDefault> updateOrderById(@PathVariable Long id, @Validated @RequestBody OrderDtoDefault orderDto) {
        orderDto = orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok().body(orderDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<OrderDtoDefault> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
