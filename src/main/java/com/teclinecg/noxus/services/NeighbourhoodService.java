package com.teclinecg.noxus.services;

import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.NeighbourhoodModel;
import com.teclinecg.noxus.models.SizeModel;
import com.teclinecg.noxus.repositories.NeighbourhoodRepository;
import com.teclinecg.noxus.repositories.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NeighbourhoodService {

    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;

    public NeighbourhoodModel findNeighbourhoodById(Long id) {
        Optional<NeighbourhoodModel> optionalNeighbourhoodModel = neighbourhoodRepository.findById(id);
        if (optionalNeighbourhoodModel.isPresent()) {
            return optionalNeighbourhoodModel.get();
        } else {
            throw new ResourceNotFoundException("Resource: Neighbourhood. Not found with id: " + id);
        }
    }
}
