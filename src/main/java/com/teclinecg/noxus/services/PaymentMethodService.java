package com.teclinecg.noxus.services;

import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.DeliveryTaxModel;
import com.teclinecg.noxus.models.PaymentMethodModel;
import com.teclinecg.noxus.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodModel findPaymentMethodById(Long id) {
        Optional<PaymentMethodModel> optionalPaymentMethodModel = paymentMethodRepository.findById(id);
        if (optionalPaymentMethodModel.isPresent()) {
            return optionalPaymentMethodModel.get();
        } else {
            throw new ResourceNotFoundException("Resource: Payment Method. Not found with id: " + id);
        }
    }
}
