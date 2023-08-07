package ui;

import model.Categorize;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represents a Remove Window for the user
public class RemoveProduct extends JFrame {
    private static final int WIDTH = 850;
    private static final int HEIGHT = 450;
    Categorize productsList;
    private JPanel panel = new JPanel();
    private JScrollPane scrollPane;


    DefaultListModel<String> productsListModel;
    JList<String> productList;
    RemoveProduct removing;

    // EFFECTS: constructs a Remove Product window for the
    //          user to remove products from the list
    public RemoveProduct(Categorize productsList) {
        super("Remove Products");
        this.productsList = productsList;

        createMainWindow();
        createLabelPanel();
        setVisible(true);
    }

    // EFFECTS: creates a frame of Main Window without the buttons in the Remove Window
    public void createMainWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo((Component) null);
        add(panel);

        JLabel removeGif = new JLabel("");
        removeGif.setIcon(new ImageIcon("./data/gifs/Remove.gif"));
        removeGif.setBounds(400, -10, 450, 400);
        panel.add(removeGif);
    }

    // EFFECTS: creates the list of products and all buttons in the Remove Window
    public void createLabelPanel() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);

        printProducts();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(removeButton());
        buttonPane.add(removeAllButton());
        buttonPane.add(doneButton());
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPane, BorderLayout.PAGE_END);

    }


    // EFFECTS: prints out the products on the Remove Window to remove products
    public void printProducts() {
        productsListModel = new DefaultListModel<>();
        productList = new JList<>();
        productList.setModel(productsListModel);

        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.setFont(new Font("Arial", Font.PLAIN, 18));

        scrollPane = new JScrollPane(productList);
        scrollPane.setViewportView(productList);

        getProducts();
    }

    // EFFECTS: gets all the products from the productsList
    public void getProducts() {
        productsListModel.addElement("Please Select From the Following To Remove: ");
        ArrayList<Product> products = productsList.getProductsList();
        if (products.size() == 0) {
            productsListModel.addElement("\nThere Are No Products To Remove !!!");
        } else {
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
        doneButton.setBounds(300, 90, 80, 25);
        doneButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        if (productsList.getProductsList().size() == 0) {
//            doneButton.setEnabled(false);
//        }
//        add(doneButton, BorderLayout.AFTER_LINE_ENDS);

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


    // MODIFIES: this, productsList
    // EFFECTS: creates Remove Button that can be used to
    //          remove a product from productsList when selected
    public JButton removeButton() {
        JButton removeButton = new JButton("Remove");
        removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getEditedList();
            }
        });
        return removeButton;
    }


    // MODIFIES: this, productsList
    // EFFECTS: creates Remove All Button that can be used
    //          to remove all products from the productsList
    public JButton removeAllButton() {
        JButton removeAllButton = new JButton("Remove All Products");
        removeAllButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        add(removeAllButton, "South");

        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == removeAllButton) {
                    if (productsList.getProductsList().size() > 0) {
                        productsList.getProductsList().removeAll(productsList.getProductsList());
                        productsListModel.removeRange(1, productsListModel.size() - 1);
//                        JOptionPane.showMessageDialog(null,
//                                "Hope You Don't Regret This Decision !!!");
                        dispose();
                        if (!isDisplayable()) {
                            new RemoveProduct(productsList);
                        }

                        removeAllDialog();
                    }
                }
            }
        });
        return removeAllButton;
    }

    // EFFECTS: creates the dialog box that is displayed when
    //          all the products are removed form the list
    public void removeAllDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(400, 260);
        JLabel goodByeGif = new JLabel("");
        goodByeGif.setIcon(new ImageIcon("./data/gifs/RemoveAll.gif"));
        goodByeGif.setBounds(400, -10, 450, 400);
        dialog.add(goodByeGif);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo((Component) null);
        dialog.setVisible(true);
        dialog.toFront();
    }


    // MODIFIES: this, productsList
    // EFFECTS: removes the selected products from the productsList
    //          and refreshes the list numbers accordingly
    public void getEditedList() {
        int selectedIndex = productList.getSelectedIndex();
        ArrayList<Product> products = productsList.getProductsList();
        ArrayList<String> removables = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            products.get(i).makeProduct();
            removables.add("  "
                    + (i + 1) + ". " + products.get(i).getProduct());
        }
        if (selectedIndex >= 0) {
            if (removables.contains(productsListModel.get(selectedIndex))) {
                productsList.removeProduct(productsList.getProductsList().get(selectedIndex - 1));
                productsListModel.remove(selectedIndex);
                dispose();
                if (!isDisplayable()) {
                    new RemoveProduct(productsList);
                }
            }
        }
    }
}
