package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.FlavorDtoDefault;
import com.teclinecg.noxus.dtos.FlavorDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.FlavorModel;
import com.teclinecg.noxus.models.FlavorModel;
import com.teclinecg.noxus.repositories.FlavorRepository;
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
public class FlavorService {

    @Autowired
    private FlavorRepository flavorRepository;

    public FlavorDtoDefault findFlavorById(Long id) {
        Optional<FlavorModel> flavorOptional = flavorRepository.findById(id);

        if (flavorOptional.isPresent()) {
            return new FlavorDtoDefault(flavorOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Flavor. Not found with id: " + id);
        }
    }

    public Page<FlavorDtoDefault> findAllFlavorsPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<FlavorModel> pagedFlavors = flavorRepository.findAll(pageable);

        return pagedFlavors.map(FlavorDtoDefault::new);
    }

    public FlavorDtoDefault saveFlavor(@Valid FlavorDtoDefault flavorDto) {
        FlavorModel flavorModel = new FlavorModel(flavorDto);
        return new FlavorDtoDefault(flavorRepository.save(flavorModel));
    }

    public FlavorDtoDefault updateFlavor(Long id, @Valid FlavorDtoDefault flavorDto) {
        Optional<FlavorModel> existentFlavorModelOptional = flavorRepository.findById(id);

        if (existentFlavorModelOptional.isPresent()) {
            FlavorModel updatedFlavorModel = new FlavorModel(flavorDto);
            BeanUtils.copyProperties(existentFlavorModelOptional, updatedFlavorModel);
            return new FlavorDtoDefault(flavorRepository.save(updatedFlavorModel));
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
