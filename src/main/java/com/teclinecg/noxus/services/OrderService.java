package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.*;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.*;
import com.teclinecg.noxus.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private CustomerAccountService customerAccountService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private DeliveryTaxService deliveryTaxService;
    @Autowired
    private PaymentMethodService paymentMethodService;
    @Autowired
    private DeliveryTypeService deliveryTypeService;
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private FlavorService flavorService;
    @Autowired
    private EdgeService edgeService;

    public OrderDto findOrderById(Long id) {
        Optional<OrderModel> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            return new OrderDto(orderOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + id);
        }
    }

    public Page<OrderDto> findAllOrdersPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<OrderModel> pagedOrders = orderRepository.findAll(pageable);

        return pagedOrders.map(OrderDto::new);
    }

    public OrderDto saveOrder(OrderPostDto orderPostDto) {
        OrderModel orderModel = new OrderModel(orderPostDto);

        List<DrinkDto> drinkDtos = drinkService.findDrinksByIds(orderPostDto.getDrinks());
        List<DrinkModel> drinkModels = new ArrayList<>();
        for (DrinkDto i :drinkDtos) {
            drinkModels.add(new DrinkModel(i));
        }
        orderModel.setDrinks(drinkModels);

        CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountService.findCustomerAccountById(orderPostDto.getCustomerAccount()));
        orderModel.setCustomerAccount(customerAccountModel);

        AddressModel addressModel = new AddressModel(addressService.findAddressById(orderPostDto.getAddress()));
        orderModel.setAddress(addressModel);

        DeliveryTaxModel deliveryTaxModel = deliveryTaxService.findDeliveryTaxById(orderPostDto.getDeliveryTax());
        orderModel.setDeliveryTax(deliveryTaxModel);

        PaymentMethodModel paymentMethodModel = paymentMethodService.findPaymentMethodById(orderPostDto.getPaymentMethod());
        orderModel.setPaymentMethod(paymentMethodModel);

        DeliveryTypeModel deliveryTypeModel = deliveryTypeService.findDeliveryTypeById(orderPostDto.getDeliveryType());
        orderModel.setDeliveryType(deliveryTypeModel);

        List<PizzaModel> pizzaModels = new ArrayList<>();
        for (PizzaPostDto i : orderPostDto.getPizzas()) {
            PizzaModel pizzaModel = new PizzaModel();

            SizeModel sizeModel = sizeService.findSizeById(i.getPizzaSize());
            pizzaModel.setPizzaSize(sizeModel);

            List<FlavorDto> flavorDtos = flavorService.findFlavorsByIds(i.getFlavors());
            List<FlavorModel> flavorModels = new ArrayList<>();
            for (FlavorDto x : flavorDtos) {
                flavorModels.add(new FlavorModel(x));
            }
            pizzaModel.setFlavors(flavorModels);

            List<EdgeDto> edgeDtos = edgeService.findEdgesByIds(i.getEdges());
            List<EdgeModel> edgeModels = new ArrayList<>();
            for (EdgeDto x : edgeDtos) {
                edgeModels.add(new EdgeModel(x));
            }
            pizzaModel.setEdges(edgeModels);
            pizzaModel.setOrder(orderModel);
            pizzaModel.setPrice(i.getPrice());

            pizzaModels.add(pizzaModel);
        }
        orderModel.setPizzas(pizzaModels);

//        List<PizzaDto> pizzaDto = pizzaService.findPizzasByOrderId(orderModel.getId());
//        for (PizzaDto i : pizzaDto) {
//            orderModel.addPizza(new PizzaModel(i));
//        }
//
//        for (PizzaPostDto i : orderPostDto.getPizzas()) {
//            i.setOrder(orderModel);
//            pizzaService.savePizza(i);
//        }

        return new OrderDto(orderRepository.save(orderModel));
    }

    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        Optional<OrderModel> existentOrderModelOptional = orderRepository.findById(id);

        if (existentOrderModelOptional.isPresent()) {
            OrderModel updatedOrderModel = new OrderModel(orderDto);
            BeanUtils.copyProperties(existentOrderModelOptional, updatedOrderModel);
            return new OrderDto(orderRepository.save(updatedOrderModel));
        } else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + id);
        }
    }

    public void deleteOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + id);
        }
    }
}
