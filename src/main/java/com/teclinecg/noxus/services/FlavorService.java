package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.FlavorDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.FlavorModel;
import com.teclinecg.noxus.repositories.FlavorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class FlavorService {

    @Autowired
    private FlavorRepository flavorRepository;

    public FlavorDto findFlavorById(Long id) {
        Optional<FlavorModel> flavorOptional = flavorRepository.findById(id);

        if (flavorOptional.isPresent()) {
            return new FlavorDto(flavorOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Flavor. Not found with id: " + id);
        }
    }

    public Page<FlavorDto> findAllFlavorsPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<FlavorModel> pagedFlavors = flavorRepository.findAll(pageable);

        return pagedFlavors.map(FlavorDto::new);
    }

    public FlavorDto saveFlavor(FlavorDto flavorDto) {
        FlavorModel flavorModel = new FlavorModel(flavorDto);
        return new FlavorDto(flavorRepository.save(flavorModel));
    }

    public FlavorDto updateFlavor(Long id, FlavorDto flavorDto) {
        Optional<FlavorModel> existentFlavorModelOptional = flavorRepository.findById(id);

        if (existentFlavorModelOptional.isPresent()) {
            FlavorModel updatedFlavorModel = new FlavorModel(flavorDto);
            BeanUtils.copyProperties(existentFlavorModelOptional, updatedFlavorModel);
            return new FlavorDto(flavorRepository.save(updatedFlavorModel));
        } else {
            throw new ResourceNotFoundException("Resource: Flavor. Not found with id: " + id);
        }
    }

    public void deleteFlavorById(Long id) {
        if (flavorRepository.existsById(id)) {
            flavorRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Flavor. Not found with id: " + id);
        }
    }
}
