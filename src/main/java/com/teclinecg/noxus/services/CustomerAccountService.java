package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CustomerAccountPostDto;
import com.teclinecg.noxus.dtos.CustomerAccountDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.models.StatusModel;
import com.teclinecg.noxus.repositories.CustomerAccountRepository;
import com.teclinecg.noxus.repositories.StatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerAccountService {

    @Autowired
    private CustomerAccountRepository customerAccountRepository;
    @Autowired
    private StatusService statusService;

    public CustomerAccountDto findCustomerAccountById(Long id) {
        Optional<CustomerAccountModel> customerAccountOptional = customerAccountRepository.findById(id);

        if (customerAccountOptional.isPresent()) {
            return new CustomerAccountDto(customerAccountOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Customer Account. Not found with id: " + id);
        }
    }

    public Page<CustomerAccountPostDto> findAllCustomerAccountsPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<CustomerAccountModel> pagedCustomerAccounts = customerAccountRepository.findAll(pageable);

        return pagedCustomerAccounts.map(CustomerAccountPostDto::new);
    }

    public CustomerAccountDto saveCustomerAccount(CustomerAccountPostDto customerAccountDto) {
        CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountDto);
        customerAccountModel.setStatus(statusService.findStatusById(customerAccountDto.getStatus()));
        return new CustomerAccountDto(customerAccountModel);
    }

    public CustomerAccountPostDto updateCustomerAccount(Long id, CustomerAccountPostDto customerAccountDto) {
        Optional<CustomerAccountModel> existentCustomerAccountModelOptional = customerAccountRepository.findById(id);

        if (existentCustomerAccountModelOptional.isPresent()) {
            CustomerAccountModel updatedCustomerAccount = new CustomerAccountModel(customerAccountDto);
            BeanUtils.copyProperties(existentCustomerAccountModelOptional, updatedCustomerAccount);
            return new CustomerAccountPostDto(customerAccountRepository.save(updatedCustomerAccount));
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
