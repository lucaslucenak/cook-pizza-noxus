package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.CreditCardPostDto;
import com.teclinecg.noxus.dtos.CreditCardDto;
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
    public ResponseEntity<Page<CreditCardPostDto>> findAllCreditCardsPaginated(Pageable pageable) {
        Page<CreditCardPostDto> creditCards = creditCardService.findAllCreditCardsPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(creditCards);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/credit-card/{creditCardId}", notes = "Returns Credit Card Selected By Id")
    public ResponseEntity<CreditCardPostDto> findCreditCardById(@PathVariable Long id) {
        CreditCardPostDto creditCard = creditCardService.findCreditCardById(id);
        return ResponseEntity.ok().body(creditCard);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/credit-card", notes = "Save a new Credit Card")
    public ResponseEntity<CreditCardDto> saveCreditCard(@Valid @RequestBody CreditCardPostDto creditCardPostDto) {
        CreditCardDto creditCardDto = creditCardService.saveCreditCard(creditCardPostDto);
        return ResponseEntity.ok().body(creditCardDto);
    }

    @PutMapping
    @ApiOperation(value = "http://localhost:8080/credit-card/{creditCardId}", notes = "Update an existing Credit Card")
    public ResponseEntity<CreditCardPostDto> updateCreditCardById(@PathVariable Long id, @Valid  @RequestBody CreditCardPostDto creditCardDto) {
        creditCardDto = creditCardService.updateCreditCard(id, creditCardDto);
        return ResponseEntity.ok().body(creditCardDto);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/credit-card/{creditCardId}", notes = "Delete an existing Credit Card")
    public ResponseEntity<CreditCardPostDto> deleteCreditCardById(@PathVariable Long id) {
        creditCardService.deleteCreditCardById(id);
        return ResponseEntity.noContent().build();
    }
}
