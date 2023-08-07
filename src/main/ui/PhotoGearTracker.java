package ui;

import model.Categorize;
import model.Product;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Photography Products Tracking Application
// Based on the code structure from TellerApp and JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class PhotoGearTracker {
    private static final String JSON_LOCATION = "./data/ProductsList.json";
    private Scanner input;
    private String category;
    private Categorize productsList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs empty products list and runs the PhotoGear Tracker application
    public PhotoGearTracker() throws FileNotFoundException {
        productsList = new Categorize("Products List");
        productsList.setCategories();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);
        runTracker();
    }

    // MODIFIES: this
    // EFFECTS: runs the user input application
    public void runTracker() {
        String inputValue;
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        int repeat = 1;

        System.out.println("\nWelcome to PhotoGear Tracker");

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

        System.out.println("\nCome Back To Make Sure Your Data Is Safe !!!");


    }

    // EFFECTS: Displays main menu to the user to choose from the following operations
    private void menu() {
        System.out.println("\nSelect from the following: ");
        System.out.println("\ta -> Add a Product");
        System.out.println("\tp -> View Products List");
        System.out.println("\tc -> View Products List By Category");
        System.out.println("\tr -> Remove a Product");
        System.out.println("\ts -> Save Your List");
        System.out.println("\tl -> Load Your Previously Saved List");
        System.out.println("\te -> Exit");
    }

    // EFFECTS: Displays category menu to the user to choose from the following categories
    private void categoryDisplayMenu() {
        System.out.println("\nChoose The Category of The Product:");
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
        } else if (inputValue.equals("p")) {
            viewProducts();
        } else if (inputValue.equals("r")) {
            removeProduct();
        } else if (inputValue.equals("s")) {
            saveProductsList();
        } else if (inputValue.equals("l")) {
            loadProductsList();
        } else {
            System.out.println("\nPlease Select Valid Choice !!!");
        }
    }

    // MODIFIES: this, productsList
    // EFFECTS: adds new products to the list
    private void addNewProduct() {
        System.out.println("\nPlease Insert Details To Add A Product:");
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

        System.out.println("\nProduct Added Successfully !!!");
    }


    // EFFECTS: takes input from the user and sets the Category
    //          to the category variable of the product
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
                System.out.println("\nPlease Choose Correct Option !!!\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Deletes a selected product from the list of products
    private void removeProduct() {
        {
            if (productsList.getProductsList().size() == 0) {
                System.out.println("\nThere Are No Products To Remove !!!");
            } else {
                int repeatAgain = 1;
                while (repeatAgain == 1) {
                    removeStatements();
                    String remInput = input.next();
                    if (remInput.equals("1")) {
                        removeOneProduct();
                        repeatAgain = 0;
                    } else if (remInput.equals("2")) {
                        removeAllProducts();
                        repeatAgain = 0;
                    } else if (remInput.equals("x")) {
                        System.out.println("\n'Remove' feels bad for not using it !!!");
                        break;
                    } else {
                        System.out.println("\nPlease Choose Correct Option !!!\n");
                    }
                }
            }
        }
    }

    // EFFECTS: prints out remove choices for user
    private void removeStatements() {
        System.out.println("Choose A Way To Throw Your Product Away:");
        System.out.println("\t1 -> Throw One Product");
        System.out.println("\t2 -> Throw All Products");
        System.out.println("\tx -> Throw You To Main Menu");
    }

    // EFFECTS: Prints out the list of products for being removed from the list
    private void removingProductsList() {
        ArrayList<Product> products = productsList.getProductsList();
        for (int i = 0; i < products.size(); i++) {
            products.get(i).makeProduct();
            String product = products.get(i).getProduct();
            int x = i + 1;
            System.out.println("\t" + x + " -> " + product);
        }
    }

    // MODIFIES: this, productsList
    // EFFECTS: removes one product that was chosen by the user
    private void removeOneProduct() {
        ArrayList<Product> products = productsList.getProductsList();
        int repeat = 1;
        while (repeat == 1) {
            System.out.println("\nPlease Select From The Following to Remove Product:");
            removingProductsList();
            System.out.println("\nChoose Any Product From Above To Remove: ");
            String productInput = input.next();
            if (Integer.parseInt(productInput) <= products.size()) {
                int a = Integer.parseInt(productInput);
                products.remove(products.get(a - 1));
                System.out.println("\nYour Product's Gone To Infinity And Beyond !!!");
                repeat = 0;
            } else {
                System.out.println("\nPlease Choose The Correct Product !!!");
            }
        }
    }


    // MODIFIES: this, productsList
    // EFFECTS: remove all products from the list of products
    private void removeAllProducts() {
        productsList.getProductsList().removeAll(productsList.getProductsList());
        System.out.println("\nHope You Don't Regret This Decision !!!");
    }


    // EFFECTS: Prints out categorized list of products with the respective categories
    //          and return a print statement if there are no items in a particular category
    private void categorizeProducts() {
        for (String category : productsList.getCategories()) {
            productsList.makeProductsListByCategory(category);
            ArrayList<Product> products = productsList.getProductsListByCategory();
            if (products.size() == 0) {
                System.out.println("\n" + category + ":");
                System.out.println("No Products In This Category !!!");
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


    // EFFECTS: Prints out the list of products in order
    private void viewProducts() {
        ArrayList<Product> products = productsList.getProductsList();
        if (products.size() == 0) {
            System.out.println("\nThere Are No Products To View !!!");
        } else {
            System.out.println("\nAll Products:");
            for (int i = 0; i < products.size(); i++) {
                products.get(i).makeProduct();
                String product = products.get(i).getProduct();
                int x = i + 1;
                System.out.println(x + ". " + product);
            }
        }
    }


    // EFFECTS: saves the products list to file
    private void saveProductsList() {
        try {
            jsonWriter.open();
            jsonWriter.write(productsList);
            jsonWriter.close();
            System.out.println("Don't Worry! Your " + productsList.getName()
                    + " Went To " + JSON_LOCATION + " And Is Very Safe.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Products List from file
    private void loadProductsList() {
        try {
            productsList = jsonReader.read();
            System.out.println("Got Back Your " + productsList.getName() + " From " + JSON_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_LOCATION);
        }
    }
}
