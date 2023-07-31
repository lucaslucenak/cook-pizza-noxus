package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.OrderDtoDefault;
import com.teclinecg.noxus.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDtoDefault>> findAllOrdersPaginated(
            @RequestParam(value = "pagQtt", defaultValue = "1") Long pagQtt,
            @RequestParam(value = "registerQtt", defaultValue = "10") Long registerQtt
    ) {
        List<OrderDtoDefault> orders = orderService.findAllOrdersPaginated(pagQtt, registerQtt);gi
    }
}
