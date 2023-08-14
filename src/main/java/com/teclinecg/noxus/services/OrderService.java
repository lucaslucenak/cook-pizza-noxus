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
        Double orderPrice = 0.0;

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
            Double pizzaPrice = 0.0;

            PizzaModel pizzaModel = new PizzaModel();

            SizeModel sizeModel = sizeService.findSizeById(i.getPizzaSize());
            pizzaModel.setPizzaSize(sizeModel);

            List<FlavorDto> flavorDtos = flavorService.findFlavorsByIds(i.getFlavors());
            List<FlavorModel> flavorModels = new ArrayList<>();
            for (FlavorDto x : flavorDtos) {
                flavorModels.add(new FlavorModel(x));
                pizzaPrice += x.getPrice();
            }
            pizzaModel.setFlavors(flavorModels);

            List<EdgeDto> edgeDtos = edgeService.findEdgesByIds(i.getEdges());
            List<EdgeModel> edgeModels = new ArrayList<>();
            for (EdgeDto x : edgeDtos) {
                edgeModels.add(new EdgeModel(x));
                pizzaPrice += x.getPrice();
            }
            pizzaModel.setEdges(edgeModels);
            pizzaModel.setOrder(orderModel);
            pizzaModel.setPrice(pizzaPrice);

            pizzaModels.add(pizzaModel);
        }
        orderModel.setPizzas(pizzaModels);

        for (PizzaModel i : orderModel.getPizzas()) {
            orderPrice += i.getPrice();
        }
        for (DrinkModel i : orderModel.getDrinks()) {
            orderPrice += i.getPrice();
        }
        orderPrice += orderModel.getDeliveryTax().getTax();
        orderModel.setOrderPrice(orderPrice);

        return new OrderDto(orderRepository.save(orderModel));
    }

    public OrderDto updateOrder(Long id, OrderPostDto orderPostDto) {
        Optional<OrderModel> existentOrderModelOptional = orderRepository.findById(id);

        if (existentOrderModelOptional.isPresent()) {
            OrderModel updatedOrderModel = new OrderModel(orderPostDto);
            Double updatedOrderPrice = 0.0;

            // Update Pizzas
            if (orderPostDto.getPizzas().size() > 0 && orderPostDto.getPizzas() != null) {
                List<PizzaModel> updatedPizzas = new ArrayList<>();
                for (PizzaPostDto i : orderPostDto.getPizzas()) {
                    PizzaModel pizzaModel = new PizzaModel();
                    Double updatedPizzaPrice = 0.0;

                    pizzaModel.setPizzaSize(sizeService.findSizeById(i.getPizzaSize()));

                    List<FlavorModel> updatedFlavors = new ArrayList<>();
                    for (FlavorDto x : flavorService.findFlavorsByIds(i.getFlavors())) {
                        updatedFlavors.add(new FlavorModel(x));
                        updatedPizzaPrice += x.getPrice();
                    }
                    pizzaModel.setFlavors(updatedFlavors);

                    List<EdgeModel> updatedEdges = new ArrayList<>();
                    for (EdgeDto x : edgeService.findEdgesByIds(i.getEdges())) {
                        updatedEdges.add(new EdgeModel(x));
                        updatedPizzaPrice += x.getPrice();
                    }
                    pizzaModel.setEdges(updatedEdges);

                    pizzaModel.setPrice(updatedPizzaPrice);
                    pizzaModel.setOrder(updatedOrderModel);
                    updatedPizzas.add(pizzaModel);
                }
                updatedOrderModel.setPizzas(updatedPizzas);
            }

            // Update Drinks
            if (orderPostDto.getDrinks().size() > 0 && orderPostDto.getDrinks() != null) {
                List<DrinkModel> updatedDrinks = new ArrayList<>();
                for (Long i : orderPostDto.getDrinks()) {
                    updatedDrinks.add(new DrinkModel(drinkService.findDrinkById(i)));
                }
                updatedOrderModel.setDrinks(updatedDrinks);
            }

            // Update Customer Account
            if (orderPostDto.getCustomerAccount() != null) {
                updatedOrderModel.setCustomerAccount(new CustomerAccountModel(customerAccountService.findCustomerAccountById(orderPostDto.getCustomerAccount())));
            }

            // Update Address
            if (orderPostDto.getAddress() != null) {
                updatedOrderModel.setAddress(new AddressModel(addressService.findAddressById(orderPostDto.getAddress())));
            }

            // Update Delivery Tax
            if (orderPostDto.getDeliveryTax() != null) {
                updatedOrderModel.setDeliveryTax(deliveryTaxService.findDeliveryTaxById(orderPostDto.getDeliveryTax()));
            }

            // Update Payment Method
            if (orderPostDto.getPaymentMethod() != null) {
                updatedOrderModel.setPaymentMethod(paymentMethodService.findPaymentMethodById(orderPostDto.getPaymentMethod()));
            }

            // Update Delivery Type
            if (orderPostDto.getDeliveryType() != null) {
                updatedOrderModel.setDeliveryType(deliveryTypeService.findDeliveryTypeById(orderPostDto.getDeliveryType()));
            }

            //Update Order Price
            for (PizzaModel i : updatedOrderModel.getPizzas()) {
                updatedOrderPrice += i.getPrice();
            }
            for (DrinkModel i : updatedOrderModel.getDrinks()) {
                updatedOrderPrice += i.getPrice();
            }
            updatedOrderPrice += updatedOrderModel.getDeliveryTax().getTax();
            updatedOrderModel.setOrderPrice(updatedOrderPrice);

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
