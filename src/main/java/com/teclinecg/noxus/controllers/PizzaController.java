package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.PizzaDtoDefault;
import com.teclinecg.noxus.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    // Example: http://localhost:8080/pizza?page=0&size=2
    public ResponseEntity<Page<PizzaDtoDefault>> findAllPizzasPaginated(Pageable pageable) {
        Page<PizzaDtoDefault> pizzas = pizzaService.findAllPizzasPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(pizzas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PizzaDtoDefault> findPizzaById(@PathVariable Long id) {
        PizzaDtoDefault pizza = pizzaService.findPizzaById(id);
        return ResponseEntity.ok().body(pizza);
    }

    @PostMapping
    public ResponseEntity<PizzaDtoDefault> savePizza(@Validated @RequestBody PizzaDtoDefault pizzaDto) {
        pizzaDto = pizzaService.savePizza(pizzaDto);
        return ResponseEntity.ok().body(pizzaDto);
    }

    @PutMapping
    public ResponseEntity<PizzaDtoDefault> updatePizzaById(@PathVariable Long id, @Validated @RequestBody PizzaDtoDefault pizzaDto) {
        pizzaDto = pizzaService.updatePizza(id, pizzaDto);
        return ResponseEntity.ok().body(pizzaDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PizzaDtoDefault> deletePizzaById(@PathVariable Long id) {
        pizzaService.deletePizzaById(id);
        return ResponseEntity.noContent().build();
    }
}
