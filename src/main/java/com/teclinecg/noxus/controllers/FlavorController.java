package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.FlavorDtoDefault;
import com.teclinecg.noxus.services.FlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/flavor")
public class FlavorController {

    @Autowired
    private FlavorService flavorService;

    @GetMapping
    // Example: http://localhost:8080/flavor?page=0&size=2
    public ResponseEntity<Page<FlavorDtoDefault>> findAllFlavorsPaginated(Pageable pageable) {
        Page<FlavorDtoDefault> flavors = flavorService.findAllFlavorsPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(flavors);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FlavorDtoDefault> findFlavorById(@PathVariable Long id) {
        FlavorDtoDefault flavor = flavorService.findFlavorById(id);
        return ResponseEntity.ok().body(flavor);
    }

    @PostMapping
    public ResponseEntity<FlavorDtoDefault> saveFlavor(@Validated @RequestBody FlavorDtoDefault flavorDto) {
        flavorDto = flavorService.saveFlavor(flavorDto);
        return ResponseEntity.ok().body(flavorDto);
    }

    @PutMapping
    public ResponseEntity<FlavorDtoDefault> updateFlavorById(@PathVariable Long id, @Validated @RequestBody FlavorDtoDefault flavorDto) {
        flavorDto = flavorService.updateFlavor(id, flavorDto);
        return ResponseEntity.ok().body(flavorDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<FlavorDtoDefault> deleteFlavorById(@PathVariable Long id) {
        flavorService.deleteFlavorById(id);
        return ResponseEntity.noContent().build();
    }
}
