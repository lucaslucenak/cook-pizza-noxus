package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.PizzaDtoDefault;
import com.teclinecg.noxus.services.PizzaService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "http://localhost:8080/pizza?page=0&size=2", notes = "Returns Pizzas Paginated")
    public ResponseEntity<Page<PizzaDtoDefault>> findAllPizzasPaginated(Pageable pageable) {
        Page<PizzaDtoDefault> pizzas = pizzaService.findAllPizzasPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(pizzas);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/pizza/{pizzaId}", notes = "Returns pizza Selected By Id")
    public ResponseEntity<PizzaDtoDefault> findPizzaById(@PathVariable Long id) {
        PizzaDtoDefault pizza = pizzaService.findPizzaById(id);
        return ResponseEntity.ok().body(pizza);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/pizza", notes = "Save a new pizza")
    public ResponseEntity<PizzaDtoDefault> savePizza( @RequestBody PizzaDtoDefault pizzaDto) {
        pizzaDto = pizzaService.savePizza(pizzaDto);
        return ResponseEntity.ok().body(pizzaDto);
    }

    @PutMapping
    @ApiOperation(value = "http://localhost:8080/pizza/{pizzaId}", notes = "Update an existing pizza")
    public ResponseEntity<PizzaDtoDefault> updatePizzaById(@PathVariable Long id,  @RequestBody PizzaDtoDefault pizzaDto) {
        pizzaDto = pizzaService.updatePizza(id, pizzaDto);
        return ResponseEntity.ok().body(pizzaDto);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/pizza/{pizzaId}", notes = "Delete an existing pizza")
    public ResponseEntity<PizzaDtoDefault> deletePizzaById(@PathVariable Long id) {
        pizzaService.deletePizzaById(id);
        return ResponseEntity.noContent().build();
    }
}
