package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.*;
import com.teclinecg.noxus.dtos.post.OrderPostDto;
import com.teclinecg.noxus.dtos.post.PizzaPostDto;
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
    public ResponseEntity<OrderDto> saveOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        return ResponseEntity.ok().body(orderService.saveOrder(orderPostDto));
    }

    @PostMapping(value = "/add-pizza-into-order")
    @ApiOperation(value = "http://localhost:8080/add-pizza-into-order", notes = "Add pizza into an existent order")
    public ResponseEntity<OrderDto> addPizzaIntoExistingOrder(@Valid @RequestBody PizzaPostDto pizzaPostDto) {
        return ResponseEntity.ok().body(orderService.addPizzaIntoExistingOrder(pizzaPostDto));
    }

    @PostMapping(value = "/add-drink-into-order/{drinkId}/{orderId}")
    @ApiOperation(value = "http://localhost:8080/1/1", notes = "Add drink into an existent order")
    public ResponseEntity<OrderDto> addPizzaIntoExistingOrder(@PathVariable Long drinkId, @PathVariable Integer drinkQuantity, @PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderService.addDrinkIntoExistingOrder(drinkId, drinkQuantity, orderId));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/order/{orderId}", notes = "Update an existing order")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable Long id, @Valid @RequestBody OrderPostDto orderPostDto) {
        return ResponseEntity.ok().body(orderService.updateOrder(id, orderPostDto));
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/order/{orderId}", notes = "Delete an existing order")
    public ResponseEntity<OrderDto> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
