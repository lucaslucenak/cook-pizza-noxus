package com.teclinecg.noxus.services;

import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.DeliveryTaxModel;
import com.teclinecg.noxus.models.StatusModel;
import com.teclinecg.noxus.repositories.DeliveryTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryTaxService {

    @Autowired
    private DeliveryTaxRepository deliveryTaxRepository;

    public DeliveryTaxModel findDeliveryTaxById(Long id) {
        Optional<DeliveryTaxModel> optionalDeliveryTaxModel = deliveryTaxRepository.findById(id);
        if (optionalDeliveryTaxModel.isPresent()) {
            return optionalDeliveryTaxModel.get();
        } else {
            throw new ResourceNotFoundException("Resource: Delivery Tax. Not found with id: " + id);
        }
    }
}
