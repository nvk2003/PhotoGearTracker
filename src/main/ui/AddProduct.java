package ui;

import model.Categorize;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents an Add Products by Category Window for the user
public class AddProduct extends JFrame {
    private static final int WIDTH = 850;
    private static final int HEIGHT = 450;
    private Container container = getContentPane();
    Categorize productsList;
    JPanel panel = new JPanel();
    JButton doneButton;
    ArrayList<String> list;

    JTextField brandText;
    JTextField modelText;
    JTextField retailerText;
    JTextField priceText;
    JComboBox<String> categoryDropdown;


    // EFFECTS: construct the window for adding products to the list
    public AddProduct(Categorize productsList) {
        super("Add Product");
        this.productsList = productsList;

        createMainWindow();
        createLabelPanel();
        setVisible(true);
    }

    // EFFECTS: creates a frame of Main Window without the buttons
    public void createMainWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo((Component) null);
        add(panel);

        JLabel addGif = new JLabel("");
        addGif.setIcon(new ImageIcon("./data/gifs/Add.gif"));
        addGif.setBounds(425, 0, 300, 400);
        panel.add(addGif);
    }


    // EFFECTS: creates all buttons, labels and text fields in the Add Window
    public void createLabelPanel() {
        panel.setLayout(null);
        panel.setBackground(Color.white);
        allLabels();
        allTextFields();
        addButton();
        doneButton();
    }

    // EFFECTS: creates all labels that can be viewed in the Add Window
    public void allLabels() {
        JLabel brand = new JLabel("Brand: ");
        brand.setBounds(40, 60, 80, 25);
        panel.add(brand);

        JLabel model = new JLabel("Model: ");
        model.setBounds(40, 120, 80, 25);
        panel.add(model);

        JLabel retailer = new JLabel("Retailer: ");
        retailer.setBounds(40, 180, 80, 25);
        panel.add(retailer);

        JLabel price = new JLabel("Price: ");
        price.setBounds(40, 240, 80, 25);
        panel.add(price);


        JLabel category = new JLabel("Category: ");
        category.setBounds(40, 300, 80, 25);
        panel.add(category);
    }


    // REQUIRES: enter price in the format 0000 or 0000.00
    // EFFECTS: creates all text field that are used to
    //          collect details from user in Add Window
    public void allTextFields() {
        brandText = new JTextField();
        brandText.setBounds(110, 60, 165, 25);
        panel.add(brandText);

        modelText = new JTextField();
        modelText.setBounds(110, 120, 165, 25);
        panel.add(modelText);

        retailerText = new JTextField();
        retailerText.setBounds(110, 180, 165, 25);
        panel.add(retailerText);

        priceText = new JTextField();
        priceText.setBounds(110, 240, 165, 25);
        panel.add(priceText);

        categoryDropdown = new JComboBox<>(getCategories());
        categoryDropdown.setBounds(110, 300, 165, 25);
        panel.add(categoryDropdown);
    }


    // MODIFIES: this, productsList
    // EFFECTS: creates add button on the Add window to add a product to the list
    public void addButton() {
        JButton button = new JButton("Add");
        button.setBounds(30, 375, 80, 25);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (brandText.getText().strip().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter brand name!");
                } else if (modelText.getText().strip().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter model name!");
                } else if (retailerText.getText().strip().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter retailer name!");
                } else if (priceText.getText().strip().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter price!");
                } else if (categoryDropdown.getSelectedItem() == "") {
                    JOptionPane.showMessageDialog(null, "Please select category!");
                } else {
                    product();
                    makeTextNull();
                }
            }
        });
    }


    // EFFECTS: creates done button that can be used to go to the Main Window
    public void doneButton() {
        doneButton = new JButton("Done");
        doneButton.setBounds(140, 375, 80, 25);
        doneButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        if (productsList.getProductsList().size() == 0) {
//            doneButton.setEnabled(false);
//        }
        panel.add(doneButton);

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == doneButton) {
                    dispose();
                }
            }
        });
    }


    // MODIFIES: this, productsList
    // EFFECTS: makes the product and adds that product to the productsList
    public void product() {
        String brand = brandText.getText();
        String model = modelText.getText();
        String retailer = retailerText.getText();
        double price = Double.parseDouble(priceText.getText());
        String category = (String) categoryDropdown.getSelectedItem();

        Product product = new Product();
        product.setBrand(brand);
        product.setModel(model);
        product.setRetailer(retailer);
        product.setPrice(price);
        product.setCategory(category);
        productsList.addProduct(product);
    }


    // EFFECTS: makes the text fields empty for the user to
    //          enter product details of another product
    public void makeTextNull() {
        brandText.setText(null);
        modelText.setText(null);
        retailerText.setText(null);
        priceText.setText(null);
        categoryDropdown.setSelectedItem("");
    }


    // EFFECTS: creates a list of categories that are
    //          shown to the user in the categoryDropDown Box
    public String[] getCategories() {
        list = new ArrayList<>();
        list = productsList.getCategories();
        if (list.get(0).equals("")) {
            //
        } else {
            list.add(0, "");
        }
        return list.toArray(new String[0]);
    }
}
