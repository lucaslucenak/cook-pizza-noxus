package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.DrinkDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageQuantityException;
import com.teclinecg.noxus.exceptions.InvalidRegisterQuantityException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.DrinkModel;
import com.teclinecg.noxus.repositories.DrinkRepository;
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
public class DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    public DrinkDtoDefault findDrinkById(Long id) {
        Optional<DrinkModel> drinkOptional = drinkRepository.findById(id);

        if (drinkOptional.isPresent()) {
            return new DrinkDtoDefault(drinkOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + id);
        }
    }

    public List<DrinkDtoDefault> findAllDrinksPaginated(Integer pagQtt, Integer registerQtt) {
        if (pagQtt < 1) {
            throw new InvalidPageQuantityException("Invalid Page Quantity. Must be greater than one");
        }
        if (registerQtt < 1) {
            throw new InvalidRegisterQuantityException("Invalid Register Quantity. Must be greater than one");
        }

        // Paginated JPA query
        Pageable pageRequest = PageRequest.of(pagQtt, registerQtt);
        Page<DrinkModel> result = drinkRepository.findAll(pageRequest);

        // Converting Models to DTOs
        List<DrinkModel> drinkModels = result.stream().toList();
        List<DrinkDtoDefault> dtos = new ArrayList<>();

        for (DrinkModel i : drinkModels) {
            dtos.add(new DrinkDtoDefault(i));
        }

        return dtos;
    }

    public DrinkDtoDefault saveDrink(@Valid DrinkDtoDefault drinkDto) {
        DrinkModel drinkModel = new DrinkModel(drinkDto);
        return new DrinkDtoDefault(drinkRepository.save(drinkModel));
    }

    public DrinkDtoDefault updateDrink(Long id, @Valid DrinkDtoDefault drinkDto) {
        Optional<DrinkModel> existentDrinkModelOptional = drinkRepository.findById(id);

        if (existentDrinkModelOptional.isPresent()) {
            DrinkModel updatedDrinkModel = new DrinkModel(drinkDto);
            BeanUtils.copyProperties(existentDrinkModelOptional, updatedDrinkModel);
            return new DrinkDtoDefault(drinkRepository.save(updatedDrinkModel));
        } else {
            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + id);
        }
    }

    public void deleteDrinkById(Long id) {
        if (drinkRepository.existsById(id)) {
            drinkRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + id);
        }
    }
}
