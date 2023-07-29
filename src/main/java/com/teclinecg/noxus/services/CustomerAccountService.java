package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CustomerAccountDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageQuantityException;
import com.teclinecg.noxus.exceptions.InvalidRegisterQuantityException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.repositories.CustomerAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerAccountService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    public CustomerAccountDtoDefault findCustomerAccountById(Long id) {
        Optional<CustomerAccountModel> customerAccountOptional = customerAccountRepository.findById(id);

        if (customerAccountOptional.isPresent()) {
            return new CustomerAccountDtoDefault(customerAccountOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Customer Account. Not found with id: " + id);
        }
    }

    public List<CustomerAccountDtoDefault> findAllCustomerAccountsPaginated(Integer pagQtt, Integer registerQtt) {
        if (pagQtt < 1) {
            throw new InvalidPageQuantityException("Invalid Page Quantity. Must be greater than one");
        }
        if (registerQtt < 1) {
            throw new InvalidRegisterQuantityException("Invalid Register Quantity. Must be greater than one");
        }

        // Paginated JPA query
        Pageable pageRequest = PageRequest.of(pagQtt, registerQtt);
        Page<CustomerAccountModel> result = customerAccountRepository.findAll(pageRequest);

        // Converting Models to DTOs
        List<CustomerAccountModel> customerAccountModels = result.stream().toList();
        List<CustomerAccountDtoDefault> dtos = new ArrayList<>();

        for (CustomerAccountModel i : customerAccountModels) {
            dtos.add(new CustomerAccountDtoDefault(i));
        }

        return dtos;
    }

    public CustomerAccountDtoDefault saveCustomerAccount(@Valid CustomerAccountDtoDefault customerAccountDto) {
        CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountDto);
        return new CustomerAccountDtoDefault(customerAccountRepository.save(customerAccountModel));
    }

    public CustomerAccountDtoDefault updateCustomerAccount(Long id, @Valid CustomerAccountDtoDefault customerAccountDto) {
        Optional<CustomerAccountModel> existentCustomerAccountModelOptional = customerAccountRepository.findById(id);

        if (existentCustomerAccountModelOptional.isPresent()) {
            CustomerAccountModel updatedCustomerAccount = new CustomerAccountModel(customerAccountDto);
            BeanUtils.copyProperties(existentCustomerAccountModelOptional, updatedCustomerAccount);
            return new CustomerAccountDtoDefault(customerAccountRepository.save(updatedCustomerAccount));
        } else {
            throw new ResourceNotFoundException("Resource: Customer Account. Not found with id: " + id);
        }
    }

    public void deleteCustomerAccountById(Long id) {
        if (customerAccountRepository.existsById(id)) {
            customerAccountRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Customer Account. Not found with id: " + id);
        }
    }
}
