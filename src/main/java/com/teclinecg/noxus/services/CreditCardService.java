package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CreditCardPostDto;
import com.teclinecg.noxus.dtos.CreditCardDto;
import com.teclinecg.noxus.dtos.CustomerAccountPostDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.repositories.CreditCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CustomerAccountService customerAccountService;

    public CreditCardDto findCreditCardById(Long id) {
        Optional<CreditCardModel> creditCardOptional = creditCardRepository.findById(id);

        if (creditCardOptional.isPresent()) {
            return new CreditCardDto(creditCardOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Credit Card. Not found with id: " + id);
        }
    }

    public Page<CreditCardDto> findAllCreditCardsPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<CreditCardModel> pagedCreditCards = creditCardRepository.findAll(pageable);

        return pagedCreditCards.map(CreditCardDto::new);
    }

    public CreditCardDto saveCreditCard(CreditCardPostDto creditCardPostDto) {
        CreditCardModel creditCardModel = new CreditCardModel(creditCardPostDto);

        // Catching the customer account to return to the Credit Card body
        CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountService.findCustomerAccountById(creditCardPostDto.getCustomerAccount()));
        creditCardModel.setCustomerAccount(customerAccountModel);

        return new CreditCardDto(creditCardRepository.save(creditCardModel));
    }

    public CreditCardDto updateCreditCard(Long id, CreditCardPostDto creditCardPostDto) {
        Optional<CreditCardModel> existentCreditCardModelOptional = creditCardRepository.findById(id);

        if (existentCreditCardModelOptional.isPresent()) {
            CreditCardModel updatedCreditCardModel = new CreditCardModel(creditCardPostDto);

            CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountService.findCustomerAccountById(creditCardPostDto.getCustomerAccount()));
            updatedCreditCardModel.setCustomerAccount(customerAccountModel);

            BeanUtils.copyProperties(existentCreditCardModelOptional, updatedCreditCardModel);
            return new CreditCardDto(creditCardRepository.save(updatedCreditCardModel));
        } else {
            throw new ResourceNotFoundException("Resource: Credit Card. Not found with id: " + id);
        }
    }

    public void deleteCreditCardById(Long id) {
        if (creditCardRepository.existsById(id)) {
            creditCardRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Credit Card. Not found with id: " + id);
        }
    }
}
