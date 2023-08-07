package ui;

import model.Categorize;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a View Products Window for the user
public class ViewProducts extends JFrame {
    private static final int WIDTH = 850;
    private static final int HEIGHT = 450;
    Categorize productsList;
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane;


    DefaultListModel<String> productsListModel;
    JList<String> productList;


    // EFFECTS: constructs a new Window to view products from productsList
    public ViewProducts(Categorize productsList) {
        super("View Products");
        this.productsList = productsList;

        createMainWindow();
        createLabelPanel();
        setVisible(true);
    }

    // EFFECTS: creates a frame of Main Window without the buttons in the View Window
    public void createMainWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo((Component) null);
        add(panel);


        JLabel viewGif = new JLabel("");
        viewGif.setIcon(new ImageIcon("./data/gifs/View.gif"));
        viewGif.setBounds(375, -10, 450, 400);
        panel.add(viewGif);
    }

    // EFFECTS: creates the list of products and all buttons in the View Window
    public void createLabelPanel() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        printProducts();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(categoryButton());
        buttonPane.add(doneButton());
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPane, BorderLayout.PAGE_END);
    }


    // EFFECTS: prints out the products on the View Window
    public void printProducts() {
        productsListModel = new DefaultListModel<>();
        productList = new JList<>(productsListModel);
        productList.setFont(new Font("Arial", Font.PLAIN, 18));

        scrollPane = new JScrollPane(productList);
        scrollPane.setViewportView(productList);

        ArrayList<Product> products = productsList.getProductsList();
        if (products.size() == 0) {
            productsListModel.addElement("\nThere Are No Products To View !!!");
        } else {
            productsListModel.addElement("\n All Products: ");
            for (int i = 0; i < products.size(); i++) {
                products.get(i).makeProduct();
                productsListModel.addElement("  "
                        + (i + 1) + ". " + products.get(i).getProduct());
            }
        }
    }


    // EFFECTS: creates done button that can be used to go to the Main Window
    public JButton doneButton() {
        JButton doneButton = new JButton("Done");
        doneButton.setBounds(80, 90, 80, 25);
        doneButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        if (productsList.getProductsList().size() == 0) {
//            doneButton.setEnabled(false);
//        }

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


    // EFFECTS: creates a category button that can be used to view products list by category
    public JButton categoryButton() {
        JButton categoryButton = new JButton("View By Category");
        categoryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == categoryButton) {
                    dispose();
                    new ViewProductsByCategory(productsList);
                }
            }
        });
        return categoryButton;
    }


}
