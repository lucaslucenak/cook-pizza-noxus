package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.DrinkDtoDefault;
import com.teclinecg.noxus.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "drink")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @GetMapping
    // Example: http://localhost:8080/drink?page=0&size=2
    public ResponseEntity<Page<DrinkDtoDefault>> findAllDrinksPaginated(Pageable pageable) {
        Page<DrinkDtoDefault> drinks = drinkService.findAllDrinksPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(drinks);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DrinkDtoDefault> findDrinkById(@PathVariable Long id) {
        DrinkDtoDefault drink = drinkService.findDrinkById(id);
        return ResponseEntity.ok().body(drink);
    }

    @PostMapping
    public ResponseEntity<DrinkDtoDefault> saveDrink(@Validated @RequestBody DrinkDtoDefault drinkDto) {
        drinkDto = drinkService.saveDrink(drinkDto);
        return ResponseEntity.ok().body(drinkDto);
    }

    @PutMapping
    public ResponseEntity<DrinkDtoDefault> updateDrinkById(@PathVariable Long id, @Validated @RequestBody DrinkDtoDefault drinkDto) {
        drinkDto = drinkService.updateDrink(id, drinkDto);
        return ResponseEntity.ok().body(drinkDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<DrinkDtoDefault> deleteDrinkById(@PathVariable Long id) {
        drinkService.deleteDrinkById(id);
        return ResponseEntity.noContent().build();
    }
}
