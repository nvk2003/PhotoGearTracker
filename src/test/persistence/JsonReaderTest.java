package persistence;


import model.Categorize;
import model.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Based on the code structure from JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Categorize productsList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyproductsList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProductsList.json");
        try {
            Categorize productsList = reader.read();
            assertEquals("Testing Empty Products List", productsList.getName());
            assertEquals(0, productsList.getProductsList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderProductsList() {
        JsonReader reader = new JsonReader("./data/testReaderProductsList.json");
        try {
            Categorize productsList = reader.read();
            assertEquals("Testing Products List", productsList.getName());
            List<Product> products = productsList.getProductsList();
            assertEquals(2, products.size());
            checkProduct("Sony", "a7iv", "UBC",
                    3099.99, "Mirrorless", products.get(0));
            checkProduct("Canon", "EOS", "SFU",
                    Double.valueOf(2095), "DSLR", products.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}