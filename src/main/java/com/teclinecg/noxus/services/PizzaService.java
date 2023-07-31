package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.PizzaDtoDefault;
import com.teclinecg.noxus.dtos.PizzaDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.PizzaModel;
import com.teclinecg.noxus.models.PizzaModel;
import com.teclinecg.noxus.repositories.PizzaRepository;
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
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public PizzaDtoDefault findPizzaById(Long id) {
        Optional<PizzaModel> pizzaOptional = pizzaRepository.findById(id);

        if (pizzaOptional.isPresent()) {
            return new PizzaDtoDefault(pizzaOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Pizza. Not found with id: " + id);
        }
    }

    public Page<PizzaDtoDefault> findAllPizzasPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<PizzaModel> pagedPizzas = pizzaRepository.findAll(pageable);

        return pagedPizzas.map(PizzaDtoDefault::new);
    }

    public PizzaDtoDefault savePizza(@Valid PizzaDtoDefault pizzaDto) {
        PizzaModel pizzaModel = new PizzaModel(pizzaDto);
        return new PizzaDtoDefault(pizzaRepository.save(pizzaModel));
    }

    public PizzaDtoDefault updatePizza(Long id, @Valid PizzaDtoDefault pizzaDto) {
        Optional<PizzaModel> existentPizzaModelOptional = pizzaRepository.findById(id);

        if (existentPizzaModelOptional.isPresent()) {
            PizzaModel updatedPizzaModel = new PizzaModel(pizzaDto);
            BeanUtils.copyProperties(existentPizzaModelOptional, updatedPizzaModel);
            return new PizzaDtoDefault(pizzaRepository.save(updatedPizzaModel));
        } else {
            throw new ResourceNotFoundException("Resource: Pizza. Not found with id: " + id);
        }
    }

    public void deletePizzaById(Long id) {
        if (pizzaRepository.existsById(id)) {
            pizzaRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Pizza. Not found with id: " + id);
        }
    }
}
