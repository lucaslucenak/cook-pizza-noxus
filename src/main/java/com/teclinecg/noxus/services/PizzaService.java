package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.EdgeDto;
import com.teclinecg.noxus.dtos.FlavorDto;
import com.teclinecg.noxus.dtos.PizzaDto;
import com.teclinecg.noxus.dtos.post.OrderPostPizzaDto;
import com.teclinecg.noxus.dtos.post.PizzaPostDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.*;
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
    @Autowired
    private OrderService orderService;

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

    public Double getPizzaPrice(OrderPostPizzaDto orderPostPizzaDto) {
        Double pizzaPrice = 0.0;

        for (FlavorDto j : flavorService.findFlavorsByIds(orderPostPizzaDto.getFlavors())) {
            pizzaPrice += j.getPrice();
        }
        for (EdgeDto j : edgeService.findEdgesByIds(orderPostPizzaDto.getEdges())) {
            pizzaPrice += j.getPrice();
        }
        return pizzaPrice;
    }

    public PizzaDto savePizza(OrderPostPizzaDto orderPostPizzaDto, OrderModel orderModel) {

        PizzaModel pizzaModel = new PizzaModel();
        Double pizzaPrice = 0.0;

        SizeModel sizeModel = sizeService.findSizeById(orderPostPizzaDto.getPizzaSize());
        pizzaModel.setPizzaSize(sizeModel);

        List<FlavorModel> flavorModels = new ArrayList<>();
        for (FlavorDto j : flavorService.findFlavorsByIds(orderPostPizzaDto.getFlavors())) {
            flavorModels.add(new FlavorModel(j));
            pizzaPrice += j.getPrice();
        }
        pizzaModel.setFlavors(flavorModels);

        List<EdgeModel> edgeModels = new ArrayList<>();
        for (EdgeDto j : edgeService.findEdgesByIds(orderPostPizzaDto.getEdges())) {
            edgeModels.add(new EdgeModel(j));
            pizzaPrice += j.getPrice();
        }
        pizzaModel.setEdges(edgeModels);
        pizzaModel.setPrice(pizzaPrice);
        pizzaModel.setOrder(orderModel);

        return new PizzaDto(pizzaRepository.save(pizzaModel));
    }

//    public PizzaDto savePizza(PizzaPostDto pizzaPostDto) {
//        PizzaModel pizzaModel = new PizzaModel(pizzaPostDto);
//        SizeModel sizeModel = sizeService.findSizeById(pizzaPostDto.getPizzaSize());
//        pizzaModel.setPizzaSize(sizeModel);
//
//        List<FlavorDto> flavorDtos = flavorService.findFlavorsByIds(pizzaPostDto.getFlavors());
//        List<FlavorModel> flavorModels = new ArrayList<>();
//        for (FlavorDto i : flavorDtos) {
//            flavorModels.add(new FlavorModel(i));
//        }
//        pizzaModel.setFlavors(flavorModels);
//
//        List<EdgeDto> edgeDtos = edgeService.findEdgesByIds(pizzaPostDto.getEdges());
//        List<EdgeModel> edgeModels = new ArrayList<>();
//        for (EdgeDto i : edgeDtos) {
//            edgeModels.add(new EdgeModel(i));
//        }
//        pizzaModel.setEdges(edgeModels);
//
//        pizzaModel.setOrder(new OrderModel(orderService.findOrderById(pizzaPostDto.getOrder())));
//
//        return new PizzaDto(pizzaRepository.save(pizzaModel));
//    }

    public PizzaDto updatePizza(Long id, PizzaPostDto pizzaPostDto) {
        Optional<PizzaModel> existentPizzaModelOptional = pizzaRepository.findById(id);

        if (existentPizzaModelOptional.isPresent()) {
            PizzaModel updatedPizzaModel = new PizzaModel(pizzaPostDto);
            Double updatedPizzaPrice = 0.0;

            // Update Order
            OrderModel updatedOrder = new OrderModel(orderService.findOrderById(pizzaPostDto.getOrder()));
            updatedPizzaModel.setOrder(updatedOrder);

            // Update Size
            SizeModel updatedSize = sizeService.findSizeById(pizzaPostDto.getPizzaSize());
            updatedPizzaModel.setPizzaSize(updatedSize);

            // Update Flavors
            List<FlavorModel> updatedFlavors = new ArrayList<>();
            for (Long i : pizzaPostDto.getFlavors()) {
                updatedFlavors.add(new FlavorModel(flavorService.findFlavorById(i)));
            }
            updatedPizzaModel.setFlavors(updatedFlavors);

            // Update Edges
            List<EdgeModel> updatedEdges = new ArrayList<>();
            for (Long i : pizzaPostDto.getEdges()) {
                updatedEdges.add(new EdgeModel(edgeService.findEdgeById(i)));
            }
            updatedPizzaModel.setEdges(updatedEdges);

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
