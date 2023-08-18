//package com.teclinecg.noxus.repositories;
//
//import com.teclinecg.noxus.enums.DeliveryTypeEnum;
//import com.teclinecg.noxus.enums.PaymentMethodEnum;
//import com.teclinecg.noxus.enums.PizzaSizeEnum;
//import com.teclinecg.noxus.enums.StatusEnum;
//import com.teclinecg.noxus.models.*;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//public class PizzaRepositoryTest {
//
//    @Autowired
//    private AddressRepository addressRepository;
//    @Autowired
//    private CustomerAccountRepository customerAccountRepository;
//    @Autowired
//    private FlavorRepository flavorRepository;
//    @Autowired
//    private EdgeRepository edgeRepository;
//    @Autowired
//    private PizzaRepository pizzaRepository;
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private StatusRepository statusRepository;
//    @Autowired
//    private SizeRepository sizeRepository;
//    @Autowired
//    private DeliveryTaxRepository deliveryTaxRepository;
//    @Autowired
//    private PaymentMethodRepository paymentMethodRepository;
//    @Autowired
//    private DeliveryTypeRepository deliveryTypeRepository;
//    @Autowired
//    private DrinkRepository drinkRepository;
//
//    private OrderModel orderModel;
//    private AddressModel addressModel;
//    private CustomerAccountModel customerAccountModel;
//    private List<FlavorModel> flavorModels;
//    private List<EdgeModel> edgeModels;
//    private List<PizzaModel> pizzaModels;
//
//    @BeforeEach
//    public void setUpBeforeEach() {
//        StatusModel statusActive = statusRepository.save(new StatusModel(StatusEnum.ACTIVE));
//
//        customerAccountModel = CustomerAccountModel.builder()
//                .firstName("Lucas")
//                .lastName("de Lucena Siqueira")
//                .cpf("108.917.264-89")
//                .email("lucas.lucenak@gmail.com")
//                .cellphoneNumber("(83) 98690-7270")
//                .status(statusActive)
//                .build();
//        customerAccountRepository.save(customerAccountModel);
//
//        addressModel = AddressModel.builder()
//                .streetName("Rua Iremar Marinho")
//                .streetNumber("83")
//                .neighbourhood("São José")
//                .city("Campina Grande")
//                .cep("58400-448")
//                .complement("Casa")
//                .referencePoint("Rua ao lado do posto maia")
//                .customerAccount(customerAccountModel)
//                .build();
//        addressRepository.save(addressModel);
//
//        flavorModels = List.of(
//                FlavorModel.builder().flavor("Frango").price(20.0).build(),
//                FlavorModel.builder().flavor("Calabresa").price(20.0).build()
//        );
//        flavorRepository.saveAll(flavorModels);
//
//        edgeModels = List.of(
//                EdgeModel.builder().edge("Tradicional").price(0.0).build(),
//                EdgeModel.builder().edge("Catupiry").price(10.0).build()
//        );
//        edgeRepository.saveAll(edgeModels);
//
//        DeliveryTaxModel deliveryTaxSaoJose = deliveryTaxRepository.save(DeliveryTaxModel.builder().tax(7.0).neighbourhood("São José").build());
//        PaymentMethodModel paymentMethodMoney = paymentMethodRepository.save(PaymentMethodModel.builder().paymentMethod(PaymentMethodEnum.MONEY).build());
//        DeliveryTypeModel deliveryTypeDelivery = deliveryTypeRepository.save(DeliveryTypeModel.builder().deliveryType(DeliveryTypeEnum.DELIVERY).build());
//        DrinkModel drinkCocaCola = drinkRepository.save(DrinkModel.builder().name("Coca Cola 1L").price(10.0).build());
//        orderModel = OrderModel.builder()
//                .orderPrice(117.0)
//                .observation("Sem cebola")
//                .dispatchDateTime(LocalDateTime.now())
//                .arrivalForecast(LocalDateTime.now().plusHours(1L))
//                .pizzas(pizzaModels)
//                .drinks(List.of(drinkCocaCola))
//                .customerAccount(customerAccountModel)
//                .address(addressModel)
//                .deliveryTax(deliveryTaxSaoJose)
//                .paymentMethod(paymentMethodMoney)
//                .deliveryType(deliveryTypeDelivery)
//                .build();
//        orderRepository.save(orderModel);
//
//        SizeModel sizeLarge = sizeRepository.save(new SizeModel(PizzaSizeEnum.LARGE));
//        pizzaModels = List.of(
//                PizzaModel.builder()
//                        .price(50.0)
//                        .order(orderModel)
//                        .pizzaSize(sizeLarge)
//                        .flavors(flavorModels)
//                        .edges(edgeModels).build(),
//                PizzaModel.builder()
//                        .price(50.0)
//                        .order(orderModel)
//                        .pizzaSize(sizeLarge)
//                        .flavors(flavorModels)
//                        .edges(edgeModels).build()
//        );
//        pizzaRepository.saveAll(pizzaModels);
//    }
//
//    @Test
//    public void PizzaRepository_FindByOrderId_ReturnPizzaList() {
//        // Arrange -> beforeEach
//
//        // Act
//        List<PizzaModel> returnedPizzas = new ArrayList<>();
//        for (Optional<PizzaModel> i : pizzaRepository.findByOrderId(orderModel.getId())) {
//            returnedPizzas.add(i.get());
//        }
//
//        // Assert
//        Assertions.assertEquals(pizzaModels, returnedPizzas);
//
//    }
//}
