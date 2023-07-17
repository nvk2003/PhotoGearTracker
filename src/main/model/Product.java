package model;


// Represents Product Details that takes Brand, Model, Retailer Name, and Price (in $) and forms a string of a product
public class Product {
    private String brand; // Brand Name
    private String model; // Model Name
    private String retailer; // Retailer Name
    private double price; // Price (in $)
    private String product;
    private String category;


    // REQUIRES: Price should be in the format 00000.00 Todo: should write anything???
    // EFFECTS: constructs a product with no details and a 0$ price TODO: EMPTY OR WITH STRINGS ???
    // todo: should we create a new product everytime???? I THINK SO
    // todo: i think we need to put information right into the product rather than take details
    // todo new change: changing from inserting details right away to setters
//    public Product(String brand, String  model, String retailer, double price, String category) {
    public Product() {
        this.brand = "";
        this.model = "";
        this.retailer = "";
        this.price = 0;
        this.category = ""; // stub
    }


    // SETTERS
    public void setBrand(String brand) {
        this.brand = brand.strip();
    }

    public void setModel(String model) {
        this.model = model.strip();
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer.strip();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category.strip();
    }


    // GETTERS
    public String getBrand() {
        return brand; // stub
    }

    public String getModel() {
        return model; // stub
    }

    public String getRetailer() {
        return retailer; // stub
    }

    public double getPrice() { // todo: change from float to String
        return price; // stub
    }

    public String getCategory() {
        return category;
    }


    // MODIFIES: this
    // EFFECTS: Combines all the details of a product and makes a single String with spaces
    public void makeProduct() {
        this.product = getBrand() + " " + getModel() + " from " + getRetailer()
                + " for $" + String.format("%.2f", getPrice()); // stub
    }

    public String getProduct() {
        return product; // stub
    }
}
