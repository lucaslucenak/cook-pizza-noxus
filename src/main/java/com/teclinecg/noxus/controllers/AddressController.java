package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.AddressDtoDefault;
import com.teclinecg.noxus.services.AddressService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    @ApiOperation(value = "http://localhost:8080/address?page=0&size=2", notes = "Returns Addresses Paginated")
    public ResponseEntity<Page<AddressDtoDefault>> findAllAddressesPaginated(Pageable pageable) {
        Page<AddressDtoDefault> addresses = addressService.findAllAddressesPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(addresses);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/address/{addressId}", notes = "Returns Address Selected By Id")
    public ResponseEntity<AddressDtoDefault> findAddressById(@PathVariable Long id) {
        AddressDtoDefault address = addressService.findAddressById(id);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/address", notes = "Save a new Address")
    public ResponseEntity<AddressDtoDefault> saveAddress(@Valid @RequestBody AddressDtoDefault addressDto) {
        addressDto = addressService.saveAddress(addressDto);
        return ResponseEntity.ok().body(addressDto);
    }

    @PutMapping
    @ApiOperation(value = "http://localhost:8080/address/{addressId}", notes = "Update an existing Address")
    public ResponseEntity<AddressDtoDefault> updateAddressById(@PathVariable Long id,  @Valid @RequestBody AddressDtoDefault addressDto) {
        addressDto = addressService.updateAddress(id, addressDto);
        return ResponseEntity.ok().body(addressDto);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/address/{addressId}", notes = "Delete an existing Address")
    public ResponseEntity<AddressDtoDefault> deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.noContent().build();
    }
}
