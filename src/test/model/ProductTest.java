package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    private Product testProduct;

    @BeforeEach
    void runBefore() {
        testProduct = new Product("Sony", "a7iv", "Kerrisdale Cameras",
                3099.99, "Mirrorless");
    }

    @Test
    void testConstructor() {
        assertEquals("Sony", testProduct.getBrand());
        assertEquals("a7iv", testProduct.getModel());
        assertEquals("Kerrisdale Cameras", testProduct.getRetailer());
        assertEquals(3099.99, testProduct.getPrice());
        assertEquals("Mirrorless", testProduct.getCategory());
    }

//    @Test
//    void testPriceIntToString() {
//        testProduct.priceIntToString();
//        assertEquals(3099.99, testProduct.getPrice());
//    }

    @Test
    void testMakeProduct() {
        testProduct.makeProduct();
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99", testProduct.getProduct());
    }
}
