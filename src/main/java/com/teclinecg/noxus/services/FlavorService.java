package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.FlavorDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageQuantityException;
import com.teclinecg.noxus.exceptions.InvalidRegisterQuantityException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
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

    public List<FlavorDtoDefault> findAllFlavorsPaginated(Integer pagQtt, Integer registerQtt) {
        if (pagQtt < 1) {
            throw new InvalidPageQuantityException("Invalid Page Quantity. Must be greater than one");
        }
        if (registerQtt < 1) {
            throw new InvalidRegisterQuantityException("Invalid Register Quantity. Must be greater than one");
        }

        // Paginated JPA query
        Pageable pageRequest = PageRequest.of(pagQtt, registerQtt);
        Page<FlavorModel> result = flavorRepository.findAll(pageRequest);

        // Converting Models to DTOs
        List<FlavorModel> flavorModels = result.stream().toList();
        List<FlavorDtoDefault> dtos = new ArrayList<>();

        for (FlavorModel i : flavorModels) {
            dtos.add(new FlavorDtoDefault(i));
        }

        return dtos;
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
