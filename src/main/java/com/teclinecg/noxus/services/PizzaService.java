package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.EdgeDto;
import com.teclinecg.noxus.dtos.FlavorDto;
import com.teclinecg.noxus.dtos.PizzaDto;
import com.teclinecg.noxus.dtos.PizzaPostDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.EdgeModel;
import com.teclinecg.noxus.models.FlavorModel;
import com.teclinecg.noxus.models.PizzaModel;
import com.teclinecg.noxus.models.SizeModel;
import com.teclinecg.noxus.repositories.PizzaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private FlavorService flavorService;
    @Autowired
    private EdgeService edgeService;

    public PizzaDto findPizzaById(Long id) {
        Optional<PizzaModel> pizzaOptional = pizzaRepository.findById(id);

        if (pizzaOptional.isPresent()) {
            return new PizzaDto(pizzaOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Pizza. Not found with id: " + id);
        }
    }

    public List<PizzaDto> findPizzasByOrderId(Long orderId) {
        List<PizzaDto> pizzaDtos = new ArrayList<>();
        for (Optional<PizzaModel> i : pizzaRepository.findByOrderId(orderId)) {
            if (i.isPresent()) {
                pizzaDtos.add(new PizzaDto(i.get()));
            } else {
                throw new ResourceNotFoundException("Resource: Pizza. Not found with order id: " + orderId);
            }
        }
        return pizzaDtos;
    }

    public Page<PizzaDto> findAllPizzasPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<PizzaModel> pagedPizzas = pizzaRepository.findAll(pageable);

        return pagedPizzas.map(PizzaDto::new);
    }

    public PizzaDto savePizza(PizzaPostDto pizzaPostDto) {
        PizzaModel pizzaModel = new PizzaModel(pizzaPostDto);
        SizeModel sizeModel = sizeService.findSizeById(pizzaPostDto.getPizzaSize());
        pizzaModel.setPizzaSize(sizeModel);

        List<FlavorDto> flavorDtos = flavorService.findFlavorsByIds(pizzaPostDto.getFlavors());
        List<FlavorModel> flavorModels = new ArrayList<>();
        for (FlavorDto i : flavorDtos) {
            flavorModels.add(new FlavorModel(i));
        }
        pizzaModel.setFlavors(flavorModels);

        List<EdgeDto> edgeDtos = edgeService.findEdgesByIds(pizzaPostDto.getEdges());
        List<EdgeModel> edgeModels = new ArrayList<>();
        for (EdgeDto i : edgeDtos) {
            edgeModels.add(new EdgeModel(i));
        }
        pizzaModel.setEdges(edgeModels);

        return new PizzaDto(pizzaRepository.save(pizzaModel));
    }

    public PizzaDto updatePizza(Long id, PizzaDto pizzaDto) {
        Optional<PizzaModel> existentPizzaModelOptional = pizzaRepository.findById(id);

        if (existentPizzaModelOptional.isPresent()) {
            PizzaModel updatedPizzaModel = new PizzaModel(pizzaDto);
            BeanUtils.copyProperties(existentPizzaModelOptional, updatedPizzaModel);
            return new PizzaDto(pizzaRepository.save(updatedPizzaModel));
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
