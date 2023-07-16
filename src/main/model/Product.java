package model;


// Represents Product Details that takes Brand, Model, Retailer Name, and Price (in $) and forms a string of a product
public class Product {
    private String brand; // Brand Name
    private String model; // Model Name
    private String retailer; // Retailer Name
    private double price; // Price (in $), TODO: BUT SHOULD THINK IF I NEED TO KEEP IT IN INT OR STRING (COZ OF LIST)
    private String product;
    private String category;
//  private String strPrice; // Price (in  String)


    // REQUIRES: Price should be in the format 00000.00 Todo: should write anything???
    // EFFECTS: constructs a product with no details and a 0$ price TODO: EMPTY OR WITH STRINGS ???
    // todo: should we create a new product everytime???? I THINK SO
    // todo: i think we need to put information right into the product rather than take details
    public Product(String brand, String  model, String retailer, double price, String category) {
        this.brand = brand;
        this.model = model;
        this.retailer = retailer;
        this.price = price;
        this.category = category; // stub
    }

//    // REQUIRES: Enter correct details
//    // MODIFIES: this
//    // EFFECTS: takes the details of the product (brand, model, retailer, and price)
//    public void takeDetails() {
//        // stub
//    }



    public String getBrand() {
        return brand; // stub
    }

    public String getModel() {
        return model; // stub
    }

    public String getRetailer() {
        return retailer; // stub
    }


//    // MODIFIES: this
//    // EFFECTS: Changes the Price parameter from 'int' to 'String'
//    public void priceIntToString() {
//        this.strPrice = String.valueOf(price); // stub
//    }

    public double getPrice() { // todo: change from float to String
        return price; // stub
    }

    public String getCategory() {
        return category;
    }


    // MODIFIES: this
    // EFFECTS: Combines all the details of a product and makes a single String with spaces
    public void makeProduct() {
        this.product = brand + " " + model + " from " + retailer + " for $" + String.valueOf(price); // stub
    }

    public String getProduct() {
        return product; // stub
    }
}
