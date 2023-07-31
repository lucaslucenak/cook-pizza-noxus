package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.CustomerAccountDtoDefault;
import com.teclinecg.noxus.services.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer-account")
public class CustomerAccountController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @GetMapping
    // Example: http://localhost:8080/customerAccount?page=0&size=2
    public ResponseEntity<Page<CustomerAccountDtoDefault>> findAllCustomerAccountsPaginated(Pageable pageable) {
        Page<CustomerAccountDtoDefault> customerAccounts = customerAccountService.findAllCustomerAccountsPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(customerAccounts);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerAccountDtoDefault> findCustomerAccountById(@PathVariable Long id) {
        CustomerAccountDtoDefault customerAccount = customerAccountService.findCustomerAccountById(id);
        return ResponseEntity.ok().body(customerAccount);
    }

    @PostMapping
    public ResponseEntity<CustomerAccountDtoDefault> saveCustomerAccount(@Validated @RequestBody CustomerAccountDtoDefault customerAccountDto) {
        customerAccountDto = customerAccountService.saveCustomerAccount(customerAccountDto);
        return ResponseEntity.ok().body(customerAccountDto);
    }

    @PutMapping
    public ResponseEntity<CustomerAccountDtoDefault> updateCustomerAccountById(@PathVariable Long id, @Validated @RequestBody CustomerAccountDtoDefault customerAccountDto) {
        customerAccountDto = customerAccountService.updateCustomerAccount(id, customerAccountDto);
        return ResponseEntity.ok().body(customerAccountDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CustomerAccountDtoDefault> deleteCustomerAccountById(@PathVariable Long id) {
        customerAccountService.deleteCustomerAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
