package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.enums.PizzaSizeEnum;
import com.teclinecg.noxus.models.*;
import org.hibernate.query.sqm.TemporalUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PizzaRepositoryTest {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private OrderRepository orderRepository;

    private AddressModel addressModel;

    @BeforeEach
    public void setUpBeforeEach() {

    }

    @Test
    public void PizzaRepository_FindByOrderId_ReturnPizzaList() {
        // Arrange
        AddressModel addressModel = AddressModel.builder().build();
        OrderModel orderModel = OrderModel.builder()
                .address(addressModel).build();

        List<PizzaModel> pizzaModels = List.of(
                PizzaModel.builder()
                        .order(orderModel).build(),
                PizzaModel.builder()
                        .order(orderModel).build()
        );
        orderRepository.save(orderModel);
        pizzaRepository.saveAll(pizzaModels);

//        OrderModel orderModel = OrderModel.builder()
//                .orderPrice(118.0)
//                .observation("Sem cebola")
//                .dispatchDateTime(LocalDateTime.now())
//                .arrivalForecast(LocalDateTime.now().plusHours(1))
//                .pizzas(List.of(
//                        PizzaModel.builder()
//                                .pizzaSize(SizeModel.builder()
//                                        .size(PizzaSizeEnum.LARGE).build()
//                                )
//                                .flavors(List.of(FlavorModel.builder()
//                                                .flavor("Frango")
//                                                .price(25.0).build())
//                                )
//                                .edges()
//
//                                .build()))
//                .address(AddressModel.builder()
//                        .streetName("R. Guarará")
//                        .streetNumber("545")
//                        .neighbourhood("Jardim Paulista")
//                        .city("São Paulo")
//                        .cep("01425-001")
//                        .complement("Casa")
//                        .referencePoint("Árvore na frente").build())
//                .
//                .build();
    }
}
