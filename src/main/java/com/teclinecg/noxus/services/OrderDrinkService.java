package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.DrinkDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.DrinkModel;
import com.teclinecg.noxus.models.OrderDrink;
import com.teclinecg.noxus.models.OrderDrinkId;
import com.teclinecg.noxus.repositories.DrinkRepository;
import com.teclinecg.noxus.repositories.OrderDrinkRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderDrinkService {

    @Autowired
    private OrderDrinkRepository orderDrinkRepository;

//    public DrinkDto findDrinksByOrderId(Long orderId) {
//        Optional<DrinkModel> drinkOptional = drinkRepository.findById(id);
//
//        if (drinkOptional.isPresent()) {
//            return new DrinkDto(drinkOptional.get());
//        } else {
//            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + id);
//        }
//    }
//
//    public List<DrinkDto> findDrinksByIds(List<Long> ids) {
//        List<DrinkDto> drinkDtos = new ArrayList<>();
//        for (DrinkModel i : drinkRepository.findAllById(ids)) {
//            drinkDtos.add(new DrinkDto(i));
//        }
//        return drinkDtos;
//    }
//
//    public Page<DrinkDto> findAllDrinksPaginated(Pageable pageable) {
//        if (pageable.getPageNumber() < 0) {
//            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
//        }
//        if (pageable.getPageSize() < 1) {
//            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
//        }
//
//        // Paginated JPA query
//        Page<DrinkModel> pagedDrinks = drinkRepository.findAll(pageable);
//
//        return pagedDrinks.map(DrinkDto::new);
//    }
//
    public OrderDrink saveOrderDrink(OrderDrink orderDrink) {
        return orderDrinkRepository.save(orderDrink);
    }
//    public DrinkDto saveDrink(DrinkDto drinkDto) {
//        DrinkModel drinkModel = new DrinkModel(drinkDto);
//        return new DrinkDto(drinkRepository.save(drinkModel));
//    }
//
//    public DrinkDto updateDrink(Long id, DrinkDto drinkDto) {
//        Optional<DrinkModel> existentDrinkModelOptional = drinkRepository.findById(id);
//
//        if (existentDrinkModelOptional.isPresent()) {
//            DrinkModel updatedDrinkModel = new DrinkModel(drinkDto);
//            BeanUtils.copyProperties(existentDrinkModelOptional, updatedDrinkModel);
//            return new DrinkDto(drinkRepository.save(updatedDrinkModel));
//        } else {
//            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + id);
//        }
//    }
//
//    public void deleteDrinkById(Long id) {
//        if (drinkRepository.existsById(id)) {
//            drinkRepository.deleteById(id);
//        }
//        else {
//            throw new ResourceNotFoundException("Resource: Drink. Not found with id: " + id);
//        }
//    }
}
