package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.AddressDtoDefault;
import com.teclinecg.noxus.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    // Example: http://localhost:8080/address?page=0&size=2
    public ResponseEntity<Page<AddressDtoDefault>> findAllAddresssPaginated(Pageable pageable) {
        Page<AddressDtoDefault> addresss = addressService.findAllAddressesPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(addresss);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDtoDefault> findAddressById(@PathVariable Long id) {
        AddressDtoDefault address = addressService.findAddressById(id);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping
    public ResponseEntity<AddressDtoDefault> saveAddress(@Validated @RequestBody AddressDtoDefault addressDto) {
        addressDto = addressService.saveAddress(addressDto);
        return ResponseEntity.ok().body(addressDto);
    }

    @PutMapping
    public ResponseEntity<AddressDtoDefault> updateAddressById(@PathVariable Long id, @Validated @RequestBody AddressDtoDefault addressDto) {
        addressDto = addressService.updateAddress(id, addressDto);
        return ResponseEntity.ok().body(addressDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<AddressDtoDefault> deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.noContent().build();
    }
}
