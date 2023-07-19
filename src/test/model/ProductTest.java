package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    private Product testProduct;

    @BeforeEach
    void runBefore() {
        testProduct = new Product();
    }

    @Test
    void testConstructor() {
        assertEquals("", testProduct.getBrand());
        assertEquals("", testProduct.getModel());
        assertEquals("", testProduct.getRetailer());
        assertEquals(0, testProduct.getPrice());
        assertEquals("", testProduct.getCategory());
    }

    @Test
    void testMakeProduct() {
        testProduct.setBrand("Sony");
        testProduct.setModel("a7iv");
        testProduct.setRetailer("Kerrisdale Cameras");
        testProduct.setPrice(3099.99);
        testProduct.setCategory("Mirrorless");

        testProduct.makeProduct();
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99", testProduct.getProduct());
    }
}
