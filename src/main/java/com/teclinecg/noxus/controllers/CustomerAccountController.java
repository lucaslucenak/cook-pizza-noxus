package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.CustomerAccountPostDto;
import com.teclinecg.noxus.dtos.CustomerAccountDto;
import com.teclinecg.noxus.services.CustomerAccountService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer-account")
public class CustomerAccountController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @GetMapping
    @ApiOperation(value = "http://localhost:8080/customer-account?page=0&size=2", notes = "Returns Customer Accounts Paginated")
    public ResponseEntity<Page<CustomerAccountDto>> findAllCustomerAccountsPaginated(Pageable pageable) {
        Page<CustomerAccountDto> customerAccounts = customerAccountService.findAllCustomerAccountsPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(customerAccounts);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/customer-account/{customerAccountId}", notes = "Returns Customer Account Selected By Id")
    public ResponseEntity<CustomerAccountDto> findCustomerAccountById(@PathVariable Long id) {
        CustomerAccountDto customerAccount = customerAccountService.findCustomerAccountById(id);
        return ResponseEntity.ok().body(customerAccount);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/customer-account", notes = "Save a new customer-account")
    public ResponseEntity<CustomerAccountDto> saveCustomerAccount(@Valid @RequestBody CustomerAccountPostDto customerAccountPostDto) {
        CustomerAccountDto customerAccountDto = customerAccountService.saveCustomerAccount(customerAccountPostDto);
        return ResponseEntity.ok().body(customerAccountDto);
    }

    @PutMapping
    @ApiOperation(value = "http://localhost:8080/customer-account/{customerAccountId}", notes = "Update an existing Customer Account")
    public ResponseEntity<CustomerAccountPostDto> updateCustomerAccountById(@PathVariable Long id, @Valid @RequestBody CustomerAccountPostDto customerAccountDto) {
        customerAccountDto = customerAccountService.updateCustomerAccount(id, customerAccountDto);
        return ResponseEntity.ok().body(customerAccountDto);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/customer-account/{customerAccountId}", notes = "Delete an existing Customer Account")
    public ResponseEntity<CustomerAccountPostDto> deleteCustomerAccountById(@PathVariable Long id) {
        customerAccountService.deleteCustomerAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
