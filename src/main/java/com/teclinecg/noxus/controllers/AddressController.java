package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.AddressDto;
import com.teclinecg.noxus.dtos.AddressPostDto;
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
    public ResponseEntity<Page<AddressDto>> findAllAddressesPaginated(Pageable pageable) {
        Page<AddressDto> addresses = addressService.findAllAddressesPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(addresses);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/address/{addressId}", notes = "Returns Address Selected By Id")
    public ResponseEntity<AddressDto> findAddressById(@PathVariable Long id) {
        AddressDto address = addressService.findAddressById(id);
        return ResponseEntity.ok().body(address);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/address", notes = "Save a new Address")
    public ResponseEntity<AddressDto> saveAddress(@Valid @RequestBody AddressPostDto addressPostDto) {
        return ResponseEntity.ok().body(addressService.saveAddress(addressPostDto));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/address/{addressId}", notes = "Update an existing Address")
    public ResponseEntity<AddressDto> updateAddressById(@PathVariable Long id, @Valid @RequestBody AddressPostDto addressPostDto) {
        return ResponseEntity.ok().body(addressService.updateAddress(id, addressPostDto));
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/address/{addressId}", notes = "Delete an existing Address")
    public ResponseEntity<AddressPostDto> deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.noContent().build();
    }
}
