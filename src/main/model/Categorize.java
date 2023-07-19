package model;

import java.util.ArrayList;

// Represents a  list of products that is categorized by category Todo: (for now) and  have to include other filters
public class Categorize {
    private ArrayList<Product> productsList;
    private ArrayList<Product> categoryList;
    private ArrayList<String> categories = new ArrayList<>();


    // EFFECTS: creates a list of products
    public Categorize() {
        this.productsList = new ArrayList<>();
    }

    public void setCategories() {
        categories.add("DSLR");
        categories.add("Mirrorless");
        categories.add("Lens");
        categories.add("Memory Card");
        categories.add("Light");
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
        return categoryList; // stub
    }
}
