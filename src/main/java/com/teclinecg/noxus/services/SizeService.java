package com.teclinecg.noxus.services;

import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.SizeModel;
import com.teclinecg.noxus.models.StatusModel;
import com.teclinecg.noxus.repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    public SizeModel findSizeById(Long id) {
        Optional<SizeModel> optionalSizeModel = sizeRepository.findById(id);
        if (optionalSizeModel.isPresent()) {
            return optionalSizeModel.get();
        } else {
            throw new ResourceNotFoundException("Resource: Size. Not found with id: " + id);
        }
    }
}
