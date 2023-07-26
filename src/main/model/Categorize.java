package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a  list of products that is categorized by category and remove products from list
public class Categorize implements Writable {
    private ArrayList<Product> productsList;
    private ArrayList<Product> categoryList;
    private ArrayList<String> categories;
    private String name;


    // EFFECTS: creates a list of products
    public Categorize(String name) {
        this.name = name;
        this.productsList = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public void setCategories() {
        categories.add("DSLR");
        categories.add("Mirrorless");
        categories.add("Lens");
        categories.add("Memory Card");
        categories.add("Light");
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<Product> getProductsList() {
        return productsList; // stub
    }

    // MODIFIES: this, productsList
    // EFFECTS: adds a new product to the list of products
    public void addProduct(Product product) {
        this.productsList.add(product);
    }


    // MODIFIES: this, productsList
    // EFFECTS: deletes a product that is selected from the list of products
    public void removeProduct(Product product) {
        this.productsList.remove(product);
    }

    // MODIFIES: this, categoryList
    // EFFECTS: check the whole productsList and filters out the products that are of same category
    public void makeProductsListByCategory(String category) {
        this.categoryList = new ArrayList<>();
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getCategory().equals(category)) {
                categoryList.add(productsList.get(i));
            }
        }
    }

    public ArrayList<Product> getProductsListByCategory() {
        return categoryList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("productsList", productsListToJson());
        return json;
    }

    // EFFECTS: returns products in this Products List as a JSON array
    private JSONArray productsListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Product product : productsList) {
            jsonArray.put(product.toJson());
        }

        return jsonArray;
    }
}
