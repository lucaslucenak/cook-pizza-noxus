package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CreditCardDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.repositories.CreditCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

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

    public CreditCardDtoDefault saveCreditCart(@Valid CreditCardDtoDefault creditCardDto) {
        CreditCardModel creditCardModel = new CreditCardModel(creditCardDto);
        return new CreditCardDtoDefault(creditCardRepository.save(creditCardModel));
    }

    public CreditCardDtoDefault updateCreditCard(Long id, @Valid CreditCardDtoDefault creditCardDto) {
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
