package persistence;

import model.Product;
import model.Categorize;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads ProductsList from JSON data stored in file
// Based on the code structure from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ProductsList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Categorize read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProductsList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ProductsList from JSON object and returns it
    private Categorize parseProductsList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Categorize productsList = new Categorize(name);
        productsList.setCategories();
        addProducts(productsList, jsonObject);
        return productsList;
    }

    // MODIFIES: productsList
    // EFFECTS: parses Products from JSON object and adds them to ProductsList
    private void addProducts(Categorize productsList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("productsList");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(productsList, nextProduct);
        }
    }

    // MODIFIES: productsList
    // EFFECTS: parses Product from JSON object and adds it to ProductsList
    private void addProduct(Categorize productsList, JSONObject jsonObject) {
        String brand = jsonObject.getString("brand");
        String model = jsonObject.getString("model");
        String retailer = jsonObject.getString("retailer");
        Double price = jsonObject.getDouble("price");
        String category = jsonObject.getString("category");

        Product product = new Product();
        product.setBrand(brand);
        product.setModel(model);
        product.setRetailer(retailer);
        product.setPrice(price);
        product.setCategory(category);
        productsList.addProduct(product);
    }
}
