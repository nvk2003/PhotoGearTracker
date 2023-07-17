//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CategorizeTest {
//    private Categorize testCategorize;
//
//    @BeforeEach
//    void runBefore() {
//        testCategorize = new Categorize();
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals(0, testCategorize.getProductsList().size());
//    }
//
//    @Test
//    void testAddProduct_One() {
//        Product product = new Product("Sony", "a7iv", "Kerrisdale Cameras",
//                3099.99, "Mirrorless");
//        testCategorize.addProduct(product);
//        testCategorize.getProductsList().get(0).makeProduct();
//        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
//                testCategorize.getProductsList().get(0).getProduct());
//        assertEquals(1, testCategorize.getProductsList().size());
//    }
//
//    @Test
//    void testAddProduct_Multiple() {
//        Product product1 = new Product("Sony", "a7iv", "Kerrisdale Cameras",
//                3099.99, "Mirrorless");
//        testCategorize.addProduct(product1);
//        testCategorize.getProductsList().get(0).makeProduct();
//        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
//                testCategorize.getProductsList().get(0).getProduct());
//        assertEquals(1, testCategorize.getProductsList().size());
//
//        Product product2 = new Product("Sony", "a7iii", "Broadway Cameras",
//                2299.99, "Mirrorless");
//        testCategorize.addProduct(product2);
//        testCategorize.getProductsList().get(1).makeProduct();
//        assertEquals("Sony a7iii from Broadway Cameras for $2299.99",
//                testCategorize.getProductsList().get(1).getProduct());
//        assertEquals(2, testCategorize.getProductsList().size());
//    }
//
//    @Test
//    void testMakeProductsListByCategory() {
//        Product product1 = new Product("Sony", "a7iv", "Kerrisdale Cameras",
//                3099.99, "Mirrorless");
//        testCategorize.addProduct(product1);
//        testCategorize.getProductsList().get(0).makeProduct();
//        assertEquals("Sony a7iv from Kerrisdale Cameras for $3099.99",
//                testCategorize.getProductsList().get(0).getProduct());
//        assertEquals(1, testCategorize.getProductsList().size());
//
//        Product product2 = new Product("Sony", "a7iii", "Broadway Cameras",
//                2299.99, "Mirrorless");
//        testCategorize.addProduct(product2);
//        testCategorize.getProductsList().get(1).makeProduct();
//        assertEquals("Sony a7iii from Broadway Cameras for $2299.99",
//                testCategorize.getProductsList().get(1).getProduct());
//        assertEquals(2, testCategorize.getProductsList().size());
//
//        Product product3 = new Product("Nikon", "D7500", "Henry's Cameras",
//                1077.99, "DSLR");
//        testCategorize.addProduct(product3);
//        testCategorize.getProductsList().get(2).makeProduct();
//        assertEquals("Nikon D7500 from Henry's Cameras for $1077.99",
//                testCategorize.getProductsList().get(2).getProduct());
//        assertEquals(3, testCategorize.getProductsList().size());
//
//
//        testCategorize.makeProductsListByCategory("DSLR");
//        assertEquals(product3, testCategorize.getProductsListByCategory().get(0));
//
//        testCategorize.makeProductsListByCategory("Mirrorless");
//        assertEquals(product1, testCategorize.getProductsListByCategory().get(0));
//        assertEquals(product2, testCategorize.getProductsListByCategory().get(1));
//    }
//}
