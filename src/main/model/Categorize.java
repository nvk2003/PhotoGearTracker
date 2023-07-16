package model;

import java.util.ArrayList;

// Represents a  list of products that is categorized by category Todo: (for now) and  have to include other filters
public class Categorize {
    private ArrayList<Product> productsList;
    private String productCategory;
//    private Product product;

    // EFFECTS: creates a list of products
    public Categorize() {
        this.productsList = new ArrayList<>();
    }


    public ArrayList<Product> getProductsList() {
        return productsList; // stub
    }

    // MODIFIES: this, productsList
    // EFFECTS: adds a new product to the list of products
    public void addProduct(Product product) {
        this.productsList.add(product);
    }

    // MODIFIES: this
    // EFFECTS: check the whole productsList and filters out the products that are of same category
    public void makeProductsListByCategory(String category) {
         // stub
    }

    public ArrayList<Product> getProductsListByCategory() {
        return null; // stub
    }
}
