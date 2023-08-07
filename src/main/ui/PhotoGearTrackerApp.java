package ui;

// Imports

import model.Categorize;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a PhotoGearTrackerApp Main Window for the user to start using the application
public class PhotoGearTrackerApp extends JFrame {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 500;
    private static final String JSON_LOCATION = "./data/GUIProductsList.json";
    private final Cursor cur = new Cursor(Cursor.HAND_CURSOR);
    Categorize productsList;
    private Container container = getContentPane();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    private AddProduct adding;
    private ViewProducts viewing;
    private RemoveProduct removing;


    // EFFECTS: constructs the GUI for the application
    public PhotoGearTrackerApp() {
        super("PhotoGear Tracker");
        productsList = new Categorize("GUI Products List");
        productsList.setCategories();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);

        createMainWindow();
        createButtons();
        setVisible(true);

    }


    // EFFECTS: creates a frame of Main Window without the buttons
    public void createMainWindow() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo((Component) null);
        container.setBackground(Color.white);
        container.setLayout(null);

        getMainWindowLabels();


    }

    // EFFECTS: sets the gifs and logo in the Main Window
    public void getMainWindowLabels() {
        ImageIcon icon = new ImageIcon("./data/gifs/Photo.png");
        Image image = icon.getImage();
        Image resizeImage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizeImage);


        JLabel logo = new JLabel("");
        logo.setIcon(icon);
        logo.setBounds(240, -145, 800, 400);
        container.add(logo);

        JLabel cameraGif = new JLabel("");
        cameraGif.setIcon(new ImageIcon("./data/gifs/Camera.gif"));
        cameraGif.setBounds(31, 50, 300, 400);
        container.add(cameraGif);

        JLabel toDoGif = new JLabel("");
        toDoGif.setIcon(new ImageIcon("./data/gifs/ToDoList.gif"));
        toDoGif.setBounds(610, 143, 200, 200);
        container.add(toDoGif);
    }

    // EFFECTS: Adds all buttons to the Main Window
    private void createButtons() {
        addButton();
        viewButton();
        removeButton();
        saveButton();
        loadButton();
        quitButton();
    }

    // MODIFIES: this, productsList
    // EFFECTS: creates add button on the main window to add a product to the list
    private void addButton() {
        JButton addButton = new JButton("Add Products");
        addButton.setBounds(360, 100, 170, 50);
        container.add(addButton);
        addButton.setCursor(cur);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addButton) {
                    if (adding != null) {
                        adding.dispose();
                    }
                    adding = new AddProduct(productsList);
                    adding.setVisible(true);
                }
            }
        });

    }


    // MODIFIES: this, productsList
    // EFFECTS: creates view button on the main window to view the products in the list
    private void viewButton() {
        JButton viewButton = new JButton("View Products");
        viewButton.setBounds(360, 150, 170, 50);
        container.add(viewButton);
        viewButton.setCursor(cur);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (viewing != null) {
                    viewing.dispose();
                }
                viewing = new ViewProducts(productsList);
                viewing.setVisible(true);

            }
        });
    }


    // MODIFIES: this, productsList
    // EFFECTS: creates remove button on the main window to remove products from list
    private void removeButton() {
        JButton removeButton = new JButton("Remove Products");
        removeButton.setBounds(360, 200, 170, 50);
        container.add(removeButton);
        removeButton.setCursor(cur);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (removing != null) {
                    removing.dispose();
                }
                removing = new RemoveProduct(productsList);
                removing.setVisible(true);
            }
        });
    }


    // MODIFIES: this, productsList
    // EFFECTS: creates save button on the main window to save the productsList
    private void saveButton() {
        JButton saveButton = new JButton("Save Products");
        saveButton.setBounds(360, 250, 170, 50);
        container.add(saveButton);
        saveButton.setCursor(cur);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(productsList);
                    jsonWriter.close();
//                    JOptionPane.showMessageDialog(null, "Don't Worry! Your "
//                            + productsList.getName() + " Went To " + JSON_LOCATION + " And Is Very Safe.");
                    saveDialog();
                } catch (FileNotFoundException a) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to write to file: " + JSON_LOCATION);
                }
            }
        });
    }

    // MODIFIES: this, productsList
    // EFFECTS: creates load button on the main window to load products from the save productsList
    private void loadButton() {
        JButton loadButton = new JButton("Load Products");
        loadButton.setBounds(360, 300, 170, 50);
        container.add(loadButton);
        loadButton.setCursor(cur);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    productsList = jsonReader.read();
//                    JOptionPane.showMessageDialog(null, "Got Back Your "
//                            + productsList.getName() + " From " + JSON_LOCATION);
//                    if (dialog.isDisplayable() && dialog != null) {
//                        dialog.dispose();
//                    }
                    loadDialog();
                } catch (IOException a) {
                    JOptionPane.showMessageDialog(null,
                            "Unable to read from file: " + JSON_LOCATION);
                }
            }
        });
    }


    // EFFECTS: creates quit button on the main window to quit the application without saving
    private void quitButton() {
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(360, 350, 170, 50);
        container.add(quitButton);
        quitButton.setCursor(cur);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                dispose();
                dispose();
                quitDialog();
            }
        });
    }


    // EFFECTS: creates the dialog box that is displayed when Save button is clicked
    public void saveDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(340, 225);
        JLabel saveGif = new JLabel("");
        saveGif.setIcon(new ImageIcon("./data/gifs/Save.gif"));
        saveGif.setBounds(400, -10, 450, 400);
        dialog.add(saveGif);
        dialog.setLocationRelativeTo((Component) null);
        dialog.setResizable(false);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    // EFFECTS: creates the dialog box that is displayed when Load button is clicked
    public void loadDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(425, 270);
        JLabel loadGif = new JLabel("");
        loadGif.setIcon(new ImageIcon("./data/gifs/Load.gif"));
        loadGif.setBounds(400, -10, 450, 400);
        dialog.add(loadGif);
        dialog.setLocationRelativeTo((Component) null);
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }


    // EFFECTS: creates the dialog box that is displayed when Quit button is clicked
    public void quitDialog() {
        JDialog dialog = new JDialog();
        dialog.setSize(450, 285);
        JLabel goodByeGif = new JLabel("");
        goodByeGif.setIcon(new ImageIcon("./data/gifs/GoodBye.gif"));
        goodByeGif.setBounds(400, -10, 450, 400);
        dialog.add(goodByeGif);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo((Component) null);
        dialog.setVisible(true);

        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.dispose();
                System.exit(0);
            }
        });
    }


}
