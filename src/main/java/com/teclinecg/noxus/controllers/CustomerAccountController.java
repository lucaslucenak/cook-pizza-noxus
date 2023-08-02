package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.CustomerAccountDtoDefault;
import com.teclinecg.noxus.services.CustomerAccountService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "http://localhost:8080/customer-account?page=0&size=2", notes = "Returns Customer Accounts Paginated")
    public ResponseEntity<Page<CustomerAccountDtoDefault>> findAllCustomerAccountsPaginated(Pageable pageable) {
        Page<CustomerAccountDtoDefault> customerAccounts = customerAccountService.findAllCustomerAccountsPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(customerAccounts);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/customer-account/{customerAccountId}", notes = "Returns Customer Account Selected By Id")
    public ResponseEntity<CustomerAccountDtoDefault> findCustomerAccountById(@PathVariable Long id) {
        CustomerAccountDtoDefault customerAccount = customerAccountService.findCustomerAccountById(id);
        return ResponseEntity.ok().body(customerAccount);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/customer-account", notes = "Save a new customer-account")
    public ResponseEntity<CustomerAccountDtoDefault> saveCustomerAccount( @RequestBody CustomerAccountDtoDefault customerAccountDto) {
        customerAccountDto = customerAccountService.saveCustomerAccount(customerAccountDto);
        return ResponseEntity.ok().body(customerAccountDto);
    }

    @PutMapping
    @ApiOperation(value = "http://localhost:8080/customer-account/{customerAccountId}", notes = "Update an existing Customer Account")
    public ResponseEntity<CustomerAccountDtoDefault> updateCustomerAccountById(@PathVariable Long id,  @RequestBody CustomerAccountDtoDefault customerAccountDto) {
        customerAccountDto = customerAccountService.updateCustomerAccount(id, customerAccountDto);
        return ResponseEntity.ok().body(customerAccountDto);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/customer-account/{customerAccountId}", notes = "Delete an existing Customer Account")
    public ResponseEntity<CustomerAccountDtoDefault> deleteCustomerAccountById(@PathVariable Long id) {
        customerAccountService.deleteCustomerAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
