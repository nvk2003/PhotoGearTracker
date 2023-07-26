package persistence;


import model.Categorize;
import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Based on the code structure from JsonSerializationDemo
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Categorize productsList = new Categorize("Testing Products List");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyProductsList() {
        try {
            Categorize productsList = new Categorize("Test Empty Products List");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyProductsList.json");
            writer.open();
            writer.write(productsList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyProductsList.json");
            productsList = reader.read();
            assertEquals("Test Empty Products List", productsList.getName());
            assertEquals(0, productsList.getProductsList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterProductsList() {
        try {
            Categorize productsList = new Categorize("Test Writing Products List");
            Product product1 = new Product();
            product1.setBrand("Sony");
            product1.setModel("a7iv");
            product1.setRetailer("UBC");
            product1.setPrice(3099.99);
            product1.setCategory("Mirrorless");
            productsList.addProduct(product1);


            Product product2 = new Product();
            product2.setBrand("Canon");
            product2.setModel("EOS");
            product2.setRetailer("SFU");
            product2.setPrice(2095);
            product2.setCategory("DSLR");
            productsList.addProduct(product2);


            JsonWriter writer = new JsonWriter("./data/testWriterProductsList.json");
            writer.open();
            writer.write(productsList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterProductsList.json");
            productsList = reader.read();
            assertEquals("Test Writing Products List", productsList.getName());
            List<Product> products = productsList.getProductsList();
            assertEquals(2, products.size());
            checkProduct("Sony", "a7iv", "UBC",
                    3099.99, "Mirrorless", products.get(0));
            checkProduct("Canon", "EOS", "SFU",
                    Double.valueOf(2095), "DSLR", products.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}