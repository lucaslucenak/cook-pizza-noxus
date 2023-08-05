package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CreditCardDtoDefault;
import com.teclinecg.noxus.dtos.CreditCardDtoSavedReturn;
import com.teclinecg.noxus.dtos.CustomerAccountDtoDefault;
import com.teclinecg.noxus.dtos.CustomerAccountDtoSavedReturn;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.models.StatusModel;
import com.teclinecg.noxus.repositories.CreditCardRepository;
import com.teclinecg.noxus.repositories.CustomerAccountRepository;
import org.hibernate.Hibernate;
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

    public CreditCardDtoDefault findCreditCardById(Long id) {
        Optional<CreditCardModel> creditCardOptional = creditCardRepository.findById(id);

        if (creditCardOptional.isPresent()) {
            return new CreditCardDtoDefault(creditCardOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Credit Card. Not found with id: " + id);
        }
    }

    public Page<CreditCardDtoDefault> findAllCreditCardsPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<CreditCardModel> pagedCreditCards = creditCardRepository.findAll(pageable);

        return pagedCreditCards.map(CreditCardDtoDefault::new);
    }

    public CreditCardDtoSavedReturn saveCreditCard(CreditCardDtoDefault creditCardDto) {
        CreditCardModel creditCardModel = new CreditCardModel(creditCardDto);

        // Catching the customer account to return to the Credit Card body
        CustomerAccountDtoDefault customerAccountDtoDefault = customerAccountService.findCustomerAccountById(creditCardDto.getCustomerAccount());
        CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountDtoDefault);
        creditCardModel.setCustomerAccount(customerAccountModel);

        return new CreditCardDtoSavedReturn(creditCardRepository.save(creditCardModel));
    }

    public CreditCardDtoDefault updateCreditCard(Long id, CreditCardDtoDefault creditCardDto) {
        Optional<CreditCardModel> existentCreditCardModelOptional = creditCardRepository.findById(id);

        if (existentCreditCardModelOptional.isPresent()) {
            CreditCardModel updatedCreditCardModel = new CreditCardModel(creditCardDto);
            BeanUtils.copyProperties(existentCreditCardModelOptional, updatedCreditCardModel);
            return new CreditCardDtoDefault(creditCardRepository.save(updatedCreditCardModel));
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
