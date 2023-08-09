package com.teclinecg.noxus.services;

import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.DeliveryTaxModel;
import com.teclinecg.noxus.models.DeliveryTypeModel;
import com.teclinecg.noxus.repositories.DeliveryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryTypeService {

    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;

    public DeliveryTypeModel findDeliveryTypeById(Long id) {
        Optional<DeliveryTypeModel> optionalDeliveryTypeModel = deliveryTypeRepository.findById(id);
        if (optionalDeliveryTypeModel.isPresent()) {
            return optionalDeliveryTypeModel.get();
        } else {
            throw new ResourceNotFoundException("Resource: Delivery Type. Not found with id: " + id);
        }
    }
}
