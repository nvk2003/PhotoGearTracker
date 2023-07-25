package persistence;

import model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProduct(String brand, String model, String retailer,
                                Double price, String category, Product product) {
        assertEquals(brand, product.getBrand());
        assertEquals(model, product.getModel());
        assertEquals(retailer, product.getRetailer());
        assertEquals(price, product.getPrice());
        assertEquals(category, product.getCategory());
    }
}
