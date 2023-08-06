package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CustomerAccountDtoDefault;
import com.teclinecg.noxus.dtos.CustomerAccountDtoSavedReturn;
import com.teclinecg.noxus.enums.StatusEnum;
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
    private StatusRepository statusRepository;

    public CustomerAccountDtoDefault findCustomerAccountById(Long id) {
        Optional<CustomerAccountModel> customerAccountOptional = customerAccountRepository.findById(id);

        if (customerAccountOptional.isPresent()) {
            return new CustomerAccountDtoDefault(customerAccountOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Customer Account. Not found with id: " + id);
        }
    }

    public Page<CustomerAccountDtoDefault> findAllCustomerAccountsPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<CustomerAccountModel> pagedCustomerAccounts = customerAccountRepository.findAll(pageable);

        return pagedCustomerAccounts.map(CustomerAccountDtoDefault::new);
    }

    public CustomerAccountDtoSavedReturn saveCustomerAccount(CustomerAccountDtoDefault customerAccountDto) {
        CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountDto);

        Optional<StatusModel> optionalStatusModel = statusRepository.findById(customerAccountDto.getStatus());
        if (optionalStatusModel.isPresent()) {
            StatusModel statusModel = optionalStatusModel.get();
            customerAccountModel.setStatus(statusModel);
            return new CustomerAccountDtoSavedReturn(customerAccountRepository.save(customerAccountModel));
        } else {
            throw new ResourceNotFoundException("Resource: Status. Not found with id: " + customerAccountDto.getStatus());
        }
    }

    public CustomerAccountDtoDefault updateCustomerAccount(Long id,  CustomerAccountDtoDefault customerAccountDto) {
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
