package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.DrinkDtoDefault;
import com.teclinecg.noxus.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "drink")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @GetMapping
    public ResponseEntity<List<DrinkDtoDefault>> findAllDrinksPaginated(
            @RequestParam(value = "pagQtt", defaultValue = "1") Integer pagQtt,
            @RequestParam(value = "registerQtt", defaultValue = "") Integer registerQtt
    ) {
        List<DrinkDtoDefault> drinks = drinkService.findAllDrinksPaginated(pagQtt, registerQtt);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", pagQtt.toString());
        headers.add("Requested-Register-Qtt", registerQtt.toString());

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
