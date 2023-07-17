package ui;

import model.Categorize;
import model.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class PhotoGearTracker {
    private Scanner input;
    private String category;
    private Categorize productsList;

    // EFFECTS: runs the PhotoGear Tracker application
    public PhotoGearTracker() {
        productsList = new Categorize();
        productsList.setCategories();
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: runs the user input application
    public void runTracker() {
        String inputValue;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        int repeat = 1;

        System.out.println("Welcome to PhotoGear Tracker [ ◉¯]");

        while (repeat == 1) {
            menu();
            inputValue = input.next();
            inputValue = inputValue.toLowerCase();

            if (inputValue.equals("e")) {
                repeat = 0;
            } else {
                process(inputValue);
            }
        }

        System.out.println("\nSee You Again! ヾ(＾ ∇ ＾)");


    }

    // EFFECTS: Displays main menu to the user to choose from the following operations
    private void menu() {
        System.out.println("\nSelect from the following: ");
        System.out.println("\ta -> Add a Product");
        System.out.println("\tc -> View Products List By Category");
        System.out.println("\te -> Exit");
    }

    // EFFECTS: Displays category menu to the user to choose from the following categories
    private void categoryDisplayMenu() {
        System.out.println("Choose The Category of The Product:");
        System.out.println("\t1 -> DSLR");
        System.out.println("\t2 -> Mirrorless");
        System.out.println("\t3 -> Lens");
        System.out.println("\t4 -> Memory Card");
        System.out.println("\t5 -> Light");
    }

    //  EFFECTS: Processes the user input and run the respective methods
    private void process(String inputValue) {
        if (inputValue.equals("a")) {
            addNewProduct();
        } else if (inputValue.equals("c")) {
            categorizeProducts();
        } else {
            System.out.println("Please Select Valid Choice ˙◠˙");
        }
    }

    // MODIFIES: this, productsList
    // EFFECTS: adds new products to the list
    private void addNewProduct() {
        System.out.println("Please Insert Details To Add A Product:");
        System.out.println("Enter Brand Name: ");
        String brand = input.next();
        System.out.println("Enter Model: ");
        String model = input.next();
        System.out.println("Enter Retailer Name: ");
        String retailer = input.next();
        System.out.println("Enter Price: ");
        double price = input.nextDouble();

        setCategory();

        Product newProduct = new Product();
        newProduct.setBrand(brand);
        newProduct.setModel(model);
        newProduct.setRetailer(retailer);
        newProduct.setPrice(price);
        newProduct.setCategory(category);

        productsList.addProduct(newProduct);

        System.out.println("Product Added Successfully (˶ˆᗜˆ˵)");
    }


    // EFFECTS: takes input from the user and sets the category
    public void setCategory() {
        int shouldRepeat = 1;
        while (shouldRepeat == 1) {
            categoryDisplayMenu();
            String catInput = input.next();
            if (catInput.equals("1")) {
                category = "DSLR";
                shouldRepeat = 0;
            } else if (catInput.equals("2")) {
                category = "Mirrorless";
                shouldRepeat = 0;
            } else if (catInput.equals("3")) {
                category = "Lens";
                shouldRepeat = 0;
            } else if (catInput.equals("4")) {
                category = "Memory Card";
                shouldRepeat = 0;
            } else if (catInput.equals("5")) {
                category = "Light";
                shouldRepeat = 0;
            } else {
                System.out.println("Please Choose Correct Option");
            }
        }
    }


    // EFFECTS: Prints out categorized list of products according to their categories
    private void categorizeProducts() {

        for (String category : productsList.getCategories()) {
            productsList.makeProductsListByCategory(category);
            ArrayList<Product> products = productsList.getProductsListByCategory();
            if (products.size() == 0) {
                System.out.println("\n" + category + ":");
                System.out.println("No Items In This Category (╥﹏╥)");
            } else {
                System.out.println("\n" + category + ":");
                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getCategory().equals(category)) {
                        products.get(i).makeProduct();
                        System.out.println(products.get(i).getProduct());
                    }
                }

            }
        }
    }
    // Enter details, make a product and add it to the list DONE
    // Ask the user if the user needs to add more products DONE
    // If the user is adding a new product, add it to the productsList DONE
    // If the user is not adding the product, categorize the productsList based on
    //                                              categories and print out accordingly DONE
    // END
}
