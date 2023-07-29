package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CreditCardDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageQuantityException;
import com.teclinecg.noxus.exceptions.InvalidRegisterQuantityException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.CreditCardModel;
import com.teclinecg.noxus.repositories.CreditCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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

    public List<CreditCardDtoDefault> findAllCreditCardsPaginated(Integer pagQtt, Integer registerQtt) {
        if (pagQtt < 1) {
            throw new InvalidPageQuantityException("Invalid Page Quantity. Must be greater than one");
        }
        if (registerQtt < 1) {
            throw new InvalidRegisterQuantityException("Invalid Register Quantity. Must be greater than one");
        }

        // Paginated JPA query
        Pageable pageRequest = PageRequest.of(pagQtt, registerQtt);
        Page<CreditCardModel> result = creditCardRepository.findAll(pageRequest);

        // Converting Models to DTOs
        List<CreditCardModel> creditCardModels = result.stream().toList();
        List<CreditCardDtoDefault> dtos = new ArrayList<>();

        for (CreditCardModel i : creditCardModels) {
            dtos.add(new CreditCardDtoDefault(i));
        }

        return dtos;
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
