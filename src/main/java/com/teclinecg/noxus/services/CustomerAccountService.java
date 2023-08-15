package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CustomerAccountPostDto;
import com.teclinecg.noxus.dtos.CustomerAccountDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.models.StatusModel;
import com.teclinecg.noxus.repositories.CustomerAccountRepository;
import com.teclinecg.noxus.repositories.StatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Page<CustomerAccountDto> findAllCustomerAccountsPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<CustomerAccountModel> pagedCustomerAccounts = customerAccountRepository.findAll(pageable);

        return pagedCustomerAccounts.map(CustomerAccountDto::new);
    }

    public CustomerAccountDto saveCustomerAccount(CustomerAccountPostDto customerAccountPostDto) {
        CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountPostDto);
        customerAccountModel.setStatus(statusService.findStatusById(customerAccountPostDto.getStatus()));

        if (customerAccountPostDto.getAddresses().size() > 0 && customerAccountPostDto.getAddresses() != null) {
            for (AddressModel i : customerAccountPostDto.getAddresses()) {
                i.setCustomerAccount(customerAccountModel);
            }
        }

        if (customerAccountPostDto.getCreditCards().size() > 0 && customerAccountPostDto.getAddresses() != null) {
            for (CreditCardModel i : customerAccountPostDto.getCreditCards()) {
                i.setCustomerAccount(customerAccountModel);
            }
        }

        return new CustomerAccountDto(customerAccountRepository.save(customerAccountModel));
    }

    public CustomerAccountDto updateCustomerAccount(Long id, CustomerAccountPostDto customerAccountPostDto) {
        Optional<CustomerAccountModel> existentCustomerAccountModelOptional = customerAccountRepository.findById(id);

        if (existentCustomerAccountModelOptional.isPresent()) {
            CustomerAccountModel updatedCustomerAccount = new CustomerAccountModel(customerAccountPostDto);
            updatedCustomerAccount.setStatus(statusService.findStatusById(customerAccountPostDto.getStatus()));

            if (customerAccountPostDto.getAddresses().size() > 0 && customerAccountPostDto.getAddresses() != null) {
                for (AddressModel i : customerAccountPostDto.getAddresses()) {
                    i.setCustomerAccount(existentCustomerAccountModelOptional.get());
                }
            }

            if (customerAccountPostDto.getCreditCards().size() > 0 && customerAccountPostDto.getAddresses() != null) {
                for (CreditCardModel i : customerAccountPostDto.getCreditCards()) {
                    i.setCustomerAccount(existentCustomerAccountModelOptional.get());
                }
            }

            BeanUtils.copyProperties(existentCustomerAccountModelOptional, updatedCustomerAccount);
            return new CustomerAccountDto(customerAccountRepository.save(updatedCustomerAccount));
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
