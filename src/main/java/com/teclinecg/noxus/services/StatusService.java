package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.CustomerAccountDto;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.StatusModel;
import com.teclinecg.noxus.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public StatusModel findStatusById(Long id) {
        Optional<StatusModel> optionalStatusModel = statusRepository.findById(id);
        if (optionalStatusModel.isPresent()) {
            return optionalStatusModel.get();
        } else {
            throw new ResourceNotFoundException("Resource: Status. Not found with id: " + id);
        }

    }
}
