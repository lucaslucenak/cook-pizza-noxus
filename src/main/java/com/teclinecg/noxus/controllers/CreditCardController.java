package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.CreditCardDtoDefault;
import com.teclinecg.noxus.services.CreditCardService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/credit-card")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping
    @ApiOperation(value = "http://localhost:8080/credit-card?page=0&size=2", notes = "Returns Credit Cards Paginated")
    public ResponseEntity<Page<CreditCardDtoDefault>> findAllCreditCardsPaginated(Pageable pageable) {
        Page<CreditCardDtoDefault> creditCards = creditCardService.findAllCreditCardsPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(creditCards);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/credit-card/{creditCardId}", notes = "Returns Credit Card Selected By Id")
    public ResponseEntity<CreditCardDtoDefault> findCreditCardById(@PathVariable Long id) {
        CreditCardDtoDefault creditCard = creditCardService.findCreditCardById(id);
        return ResponseEntity.ok().body(creditCard);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/credit-card", notes = "Save a new Credit Card")
    public ResponseEntity<CreditCardDtoDefault> saveCreditCard(@Valid @RequestBody CreditCardDtoDefault creditCardDto) {
        creditCardDto = creditCardService.saveCreditCard(creditCardDto);
        return ResponseEntity.ok().body(creditCardDto);
    }

    @PutMapping
    @ApiOperation(value = "http://localhost:8080/credit-card/{creditCardId}", notes = "Update an existing Credit Card")
    public ResponseEntity<CreditCardDtoDefault> updateCreditCardById(@PathVariable Long id, @Valid  @RequestBody CreditCardDtoDefault creditCardDto) {
        creditCardDto = creditCardService.updateCreditCard(id, creditCardDto);
        return ResponseEntity.ok().body(creditCardDto);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/credit-card/{creditCardId}", notes = "Delete an existing Credit Card")
    public ResponseEntity<CreditCardDtoDefault> deleteCreditCardById(@PathVariable Long id) {
        creditCardService.deleteCreditCardById(id);
        return ResponseEntity.noContent().build();
    }
}