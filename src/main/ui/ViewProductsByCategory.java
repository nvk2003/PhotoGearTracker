package ui;

import model.Categorize;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a View Products by Category Window for the user
public class ViewProductsByCategory extends JFrame {
    private static final int WIDTH = 850;
    private static final int HEIGHT = 450;
    Categorize productsList;
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane;


    DefaultListModel<String> productsListModel;
    JList<String> productList;

    // EFFECTS: constructs a new Window to view products by category from productsList
    public ViewProductsByCategory(Categorize productsList) {
        super("View Products By Category");
        this.productsList = productsList;

        createMainWindow();
        createLabelPanel();
        setVisible(true);
    }

    // EFFECTS: creates a frame of Main Window without the
    //          buttons in the View By Category Window
    public void createMainWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo((Component) null);
        add(panel);

        JLabel addGif = new JLabel("");
        addGif.setIcon(new ImageIcon("./data/gifs/View.gif"));
        addGif.setBounds(375, -10, 450, 400);
        panel.add(addGif);


    }

    // EFFECTS: creates the list of products by category
    //          and all buttons in the view by category Window
    public void createLabelPanel() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        printProducts();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(viewAllProductsButton());
        buttonPane.add(doneButton());
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPane, BorderLayout.PAGE_END);
    }


    // EFFECTS: prints out the products by category on the View By Category Window
    public void printProducts() {
        productsListModel = new DefaultListModel<>();
        productList = new JList<>(productsListModel);
        productList.setFont(new Font("Arial", Font.PLAIN, 18));

        scrollPane = new JScrollPane(productList);
        scrollPane.setViewportView(productList);

        getProducts();
    }


    // EFFECTS: gets the products that are categorized from the productsList
    public void getProducts() {
        if (productsList.getCategories().get(0).equals("")) {
            productsList.getCategories().remove(0);
        }

        for (String category : productsList.getCategories()) {
            productsList.makeProductsListByCategory(category);
            ArrayList<Product> products = productsList.getProductsListByCategory();
            if (products.size() == 0) {
                productsListModel.addElement("\n" + category + ":");
                productsListModel.addElement("      No Products In This Category !!!");
            } else {

                productsListModel.addElement("\n" + category + ":");

                for (int i = 0; i < products.size(); i++) {
                    int x = i;
                    if (products.get(i).getCategory().equals(category)) {
                        products.get(i).makeProduct();
                        productsListModel.addElement("  "
                                + (x + 1) + ". " + products.get(i).getProduct());
                    }
                }
            }
        }
    }


    // EFFECTS: creates done button that can be used to go to the Main Window
    public JButton doneButton() {
        JButton doneButton = new JButton("Done");
        doneButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == doneButton) {
                    dispose();
                }
            }
        });
        return doneButton;
    }


    // EFFECTS: creates a category button that can be
    //          used to view all the products in the list
    public JButton viewAllProductsButton() {
        JButton viewAllProductsButton = new JButton("View Products");
        viewAllProductsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        viewAllProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == viewAllProductsButton) {
                    dispose();
                    new ViewProducts(productsList);
                }
            }
        });
        return viewAllProductsButton;
    }
}
