package com.kushan.priceenginecloud.controller;

import com.kushan.priceenginecloud.dto.ProductDto;
import com.kushan.priceenginecloud.dto.ProductPriceDto;
import com.kushan.priceenginecloud.enums.ProductLabel;
import com.kushan.priceenginecloud.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    private final double delta = 0.000001;

    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        List<ProductDto> products = new ArrayList<>();
        ProductDto product1 = new ProductDto();
        product1.setId(1L);
        product1.setProductName("Penguin-ears");
        product1.setUnits(20);
        product1.setPrice(175.0);
        product1.setLabel(ProductLabel.RARE);

        ProductDto product2 = new ProductDto();
        product2.setId(1L);
        product2.setProductName("Horseshoe");
        product2.setUnits(5);
        product2.setPrice(825.0);
        product2.setLabel(ProductLabel.REGULAR);

        products.add(product1);
        products.add(product2);

        List<ProductPriceDto> productPriceDtos = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            productPriceDtos.add(new ProductPriceDto());
        }

        Mockito.when(productService.getProducts()).thenReturn(products);
        Mockito.when(productService.getPriceForProductAndQuantity(eq(2L), any(Integer[].class))).thenReturn(productPriceDtos);
        Mockito.when(productService.getPriceForProductAndQuantity(eq(3L), any(Integer[].class))).thenThrow(new IllegalArgumentException("Invalid product id"));
        Mockito.when(productService.getPriceForProductAndQuantity(1L, new Integer[]{-1})).thenThrow(new IllegalArgumentException("Invalid quantity"));
    }

    @Test
    @DisplayName("Get all products")
    void testGetAllProducts() {
        ResponseEntity<List<ProductDto>> products = productController.getAllProducts();
        Assertions.assertAll(
                () -> Assertions.assertEquals(products.getStatusCode(), HttpStatus.OK),
                () -> assertNotNull(products.getBody()),
                () -> Assertions.assertEquals(products.getBody().size(), 2)
        );
    }

    @Test
    @DisplayName("Get prices for 50 products")
    void testGetProductPrices() {
        ResponseEntity<?> productPrices = productController.getProductPriceList(2L, 50);
        Assertions.assertAll(
                () -> Assertions.assertEquals(productPrices.getStatusCode(), HttpStatus.OK),
                () -> assertNotNull(productPrices.getBody()),
                () -> Assertions.assertEquals(((List<ProductPriceDto>) productPrices.getBody()).size(), 50)
        );
    }

    @Test
    @DisplayName("Get prices for a product by product id and quantity")
    void testGetProductPriceByProductAndQuantity() {
        ResponseEntity<?> price = productController.getProductPrice(2L, 1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(price.getStatusCode(), HttpStatus.OK),
                () -> assertNotNull(price.getBody())
        );
    }

    @Test
    @DisplayName("Get prices for 50 products passing invalid product id")
    void testGetProductPricesPassingInvalidProductId() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productController.getProductPriceList(3L, 50);
        });
        Assertions.assertEquals(exception.getMessage(), "Invalid product id");
    }

    @Test
    @DisplayName("Get prices for a product passing invalid product id")
    void testGetProductPricePassingInvalidProductId() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productController.getProductPrice(3L, 1);
        });
        Assertions.assertEquals(exception.getMessage(), "Invalid product id");
    }

    @Test
    @DisplayName("Get prices for a product passing invalid quantity")
    void testGetProductPricePassingInvalidQuantity() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productController.getProductPrice(1L, -1);
        });
        Assertions.assertEquals(exception.getMessage(), "Invalid quantity");
    }
}
