package com.teclinecg.noxus.repositories;

import com.teclinecg.noxus.models.FlavorModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FlavorRepositoryTest {

    @Autowired
    private FlavorRepository flavorRepository;

    @Test
    public void FlavorRepository_SaveFlavor_ReturnSavedFlavor() {
        // Arrange
        FlavorModel flavorModel = FlavorModel.builder()
                .flavor("Frango")
                .price(25.0).build();

        // Act
        FlavorModel savedFlavor = flavorRepository.save(flavorModel);

        // Assert
        Assertions.assertNotNull(savedFlavor);
        Assertions.assertTrue(savedFlavor.getId() > 0);

    }
}
