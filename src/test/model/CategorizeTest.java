package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategorizeTest {
    private Categorize testCategorize;

    @BeforeEach
    void runBefore() {
        testCategorize = new Categorize();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCategorize.getProductsList().size());
    }

    @Test
    void testAddProduct_One() {
        Product product = new Product();
        product.setBrand("Sony");
        product.setModel("a7iv");
        product.setRetailer("Kerrisdale Cameras");
        product.setPrice(3099.99);
        product.setCategory("Mirrorless");

        testCategorize.addProduct(product);
        testCategorize.getProductsList().get(0).makeProduct();
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
                testCategorize.getProductsList().get(0).getProduct());
        assertEquals(1, testCategorize.getProductsList().size());
    }

    @Test
    void testAddProduct_Multiple() {
        Product product1 = new Product();
        product1.setBrand("Sony");
        product1.setModel("a7iv");
        product1.setRetailer("Kerrisdale Cameras");
        product1.setPrice(3099.99);
        product1.setCategory("Mirrorless");


        testCategorize.addProduct(product1);
        testCategorize.getProductsList().get(0).makeProduct();
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
                testCategorize.getProductsList().get(0).getProduct());
        assertEquals(1, testCategorize.getProductsList().size());

        Product product2 = new Product();
        product2.setBrand("Sony");
        product2.setModel("70-200 f/2.8");
        product2.setRetailer("Broadway Cameras");
        product2.setPrice(3499.99);
        product2.setCategory("Lens");

        testCategorize.addProduct(product2);
        testCategorize.getProductsList().get(1).makeProduct();
        assertEquals("Sony 70-200 f/2.8 from Broadway Cameras for $3499.99",
                testCategorize.getProductsList().get(1).getProduct());
        assertEquals(2, testCategorize.getProductsList().size());
    }

    @Test
    void testRemoveProduct_One() {
        Product product1 = new Product();
        product1.setBrand("Sony");
        product1.setModel("a7iv");
        product1.setRetailer("Kerrisdale Cameras");
        product1.setPrice(3099.99);
        product1.setCategory("Mirrorless");
        testCategorize.addProduct(product1);
        testCategorize.getProductsList().get(0).makeProduct();
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
                testCategorize.getProductsList().get(0).getProduct());
        assertEquals(1, testCategorize.getProductsList().size());

        Product product2 = new Product();
        product2.setBrand("Sony");
        product2.setModel("70-200 f/2.8");
        product2.setRetailer("Broadway Cameras");
        product2.setPrice(3499.99);
        product2.setCategory("Lens");
        testCategorize.addProduct(product2);
        testCategorize.getProductsList().get(1).makeProduct();
        assertEquals("Sony 70-200 f/2.8 from Broadway Cameras for $3499.99",
                testCategorize.getProductsList().get(1).getProduct());
        assertEquals(2, testCategorize.getProductsList().size());

        Product product3 = new Product();
        product3.setBrand("Nikon");
        product3.setModel("D7500");
        product3.setRetailer("Henry's Cameras");
        product3.setPrice(1077.99);
        product3.setCategory("DSLR");
        testCategorize.addProduct(product3);
        testCategorize.getProductsList().get(2).makeProduct();
        assertEquals("Nikon D7500 from Henry's Cameras for $1077.99",
                testCategorize.getProductsList().get(2).getProduct());
        assertEquals(3, testCategorize.getProductsList().size());


        testCategorize.removeProduct(product2);
        assertEquals(2, testCategorize.getProductsList().size());
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
                testCategorize.getProductsList().get(0).getProduct());
        assertEquals("Nikon D7500 from Henry's Cameras for $1077.99",
                testCategorize.getProductsList().get(1).getProduct());
    }


    @Test
    void testRemoveProduct_Multiple() {
        Product product1 = new Product();
        product1.setBrand("Sony");
        product1.setModel("a7iv");
        product1.setRetailer("Kerrisdale Cameras");
        product1.setPrice(3099.99);
        product1.setCategory("Mirrorless");
        testCategorize.addProduct(product1);
        testCategorize.getProductsList().get(0).makeProduct();
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
                testCategorize.getProductsList().get(0).getProduct());
        assertEquals(1, testCategorize.getProductsList().size());

        Product product2 = new Product();
        product2.setBrand("Sony");
        product2.setModel("70-200 f/2.8");
        product2.setRetailer("Broadway Cameras");
        product2.setPrice(3499.99);
        product2.setCategory("Lens");
        testCategorize.addProduct(product2);
        testCategorize.getProductsList().get(1).makeProduct();
        assertEquals("Sony 70-200 f/2.8 from Broadway Cameras for $3499.99",
                testCategorize.getProductsList().get(1).getProduct());
        assertEquals(2, testCategorize.getProductsList().size());

        Product product3 = new Product();
        product3.setBrand("Nikon");
        product3.setModel("D7500");
        product3.setRetailer("Henry's Cameras");
        product3.setPrice(1077.99);
        product3.setCategory("DSLR");
        testCategorize.addProduct(product3);
        testCategorize.getProductsList().get(2).makeProduct();
        assertEquals("Nikon D7500 from Henry's Cameras for $1077.99",
                testCategorize.getProductsList().get(2).getProduct());
        assertEquals(3, testCategorize.getProductsList().size());


        testCategorize.removeProduct(product2);
        assertEquals(2, testCategorize.getProductsList().size());
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
                testCategorize.getProductsList().get(0).getProduct());
        assertEquals("Nikon D7500 from Henry's Cameras for $1077.99",
                testCategorize.getProductsList().get(1).getProduct());


        testCategorize.removeProduct(product1);
        assertEquals(1, testCategorize.getProductsList().size());
        assertEquals("Nikon D7500 from Henry's Cameras for $1077.99",
                testCategorize.getProductsList().get(0).getProduct());

    }

    @Test
    void testMakeProductsListByCategory() {
        Product product1 = new Product();
        product1.setBrand("Sony");
        product1.setModel("a7iv");
        product1.setRetailer("Kerrisdale Cameras");
        product1.setPrice(3099.99);
        product1.setCategory("Mirrorless");
        testCategorize.addProduct(product1);
        testCategorize.getProductsList().get(0).makeProduct();
        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
                testCategorize.getProductsList().get(0).getProduct());
        assertEquals(1, testCategorize.getProductsList().size());

        Product product2 = new Product();
        product2.setBrand("Sony");
        product2.setModel("70-200 f/2.8");
        product2.setRetailer("Broadway Cameras");
        product2.setPrice(3499.99);
        product2.setCategory("Lens");
        testCategorize.addProduct(product2);
        testCategorize.getProductsList().get(1).makeProduct();
        assertEquals("Sony 70-200 f/2.8 from Broadway Cameras for $3499.99",
                testCategorize.getProductsList().get(1).getProduct());
        assertEquals(2, testCategorize.getProductsList().size());

        Product product3 = new Product();
        product3.setBrand("Nikon");
        product3.setModel("D7500");
        product3.setRetailer("Henry's Cameras");
        product3.setPrice(1077.99);
        product3.setCategory("DSLR");
        testCategorize.addProduct(product3);
        testCategorize.getProductsList().get(2).makeProduct();
        assertEquals("Nikon D7500 from Henry's Cameras for $1077.99",
                testCategorize.getProductsList().get(2).getProduct());
        assertEquals(3, testCategorize.getProductsList().size());

        testCategorize.setCategories();
        ArrayList category = testCategorize.getCategories();

        testCategorize.makeProductsListByCategory((String) category.get(0));
        assertEquals(product3, testCategorize.getProductsListByCategory().get(0));

        testCategorize.makeProductsListByCategory((String) category.get(1));
        assertEquals(product1, testCategorize.getProductsListByCategory().get(0));

        testCategorize.makeProductsListByCategory((String) category.get(2));
        assertEquals(product2, testCategorize.getProductsListByCategory().get(0));
    }
}
