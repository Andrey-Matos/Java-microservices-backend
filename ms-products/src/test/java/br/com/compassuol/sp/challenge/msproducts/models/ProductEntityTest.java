package br.com.compassuol.sp.challenge.msproducts.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductEntityTest {

    @Test
    public void testProductEntityConstructor() {
        // Arrange
        String name = "Test Product";
        String description = "This is a test product";
        BigDecimal price = new BigDecimal("10.99");

        // Act
        ProductEntity product = new ProductEntity(name, description, price);

        // Assert
        Assertions.assertEquals(name, product.getName());
        Assertions.assertEquals(description, product.getDescription());
        Assertions.assertEquals(price, product.getPrice());
    }

    @Test
    public void testProductEntityGettersAndSetters() {
        // Arrange
        ProductEntity product = new ProductEntity();

        // Act
        String name = "Test Product";
        String description = "This is a test product";
        BigDecimal price = new BigDecimal("10.99");

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        // Assert
        Assertions.assertEquals(name, product.getName());
        Assertions.assertEquals(description, product.getDescription());
        Assertions.assertEquals(price, product.getPrice());
    }
}