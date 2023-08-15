package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.PizzaDto;
import com.teclinecg.noxus.dtos.PizzaPostDto;
import com.teclinecg.noxus.services.PizzaService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    @ApiOperation(value = "http://localhost:8080/pizza?page=0&size=2", notes = "Returns Pizzas Paginated")
    public ResponseEntity<Page<PizzaDto>> findAllPizzasPaginated(Pageable pageable) {
        Page<PizzaDto> pizzas = pizzaService.findAllPizzasPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(pizzas);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/pizza/{pizzaId}", notes = "Returns pizza Selected By Id")
    public ResponseEntity<PizzaDto> findPizzaById(@PathVariable Long id) {
        PizzaDto pizza = pizzaService.findPizzaById(id);
        return ResponseEntity.ok().body(pizza);
    }

//    @ApiOperation(value = "http://localhost:8080/pizza", notes = "Save a new pizza")
//    public ResponseEntity<PizzaDto> savePizza(@Valid @RequestBody PizzaPostDto pizzaPostDto) {
//        return ResponseEntity.ok().body(pizzaService.savePizza(pizzaPostDto));
//    }

    @PutMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/pizza/{pizzaId}", notes = "Update an existing pizza")
    public ResponseEntity<PizzaDto> updatePizzaById(@PathVariable Long id, @Valid @RequestBody PizzaPostDto pizzaPostDto) {
        return ResponseEntity.ok().body(pizzaService.updatePizza(id, pizzaPostDto));
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/pizza/{pizzaId}", notes = "Delete an existing pizza")
    public ResponseEntity<PizzaDto> deletePizzaById(@PathVariable Long id) {
        pizzaService.deletePizzaById(id);
        return ResponseEntity.noContent().build();
    }
}
