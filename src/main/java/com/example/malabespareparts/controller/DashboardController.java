package com.example.malabespareparts.controller;

import com.example.malabespareparts.filehandler.InventoryParser;
import com.example.malabespareparts.model.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import com.example.malabespareparts.filehandler.InventoryFileHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public  class  DashboardController implements Initializable {

    private ObservableList<Part> partList;

    @FXML
    private TableView<Part> inventoryTable;

    @FXML
    private TableColumn<Part, String> partCodeColumn;

    @FXML
    private TableColumn<Part, String> descriptionColumn;

    @FXML
    private TableColumn<Part, String> brandColumn;

    @FXML
    private TableColumn<Part, Double> priceColumn;

    @FXML
    private TableColumn<Part, Integer> qtyColumn;

    @FXML
    private TableColumn<Part, String> categoryColumn;

    @FXML
    private TableColumn<Part, String> lastUpdatedColumn;

    @FXML
    private TextField keywordField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextField minPriceField;

    @FXML
    private TextField maxPriceField;

    @FXML
    private TextField partCodeField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private ComboBox<String> newCategoryComboBox;

    @FXML
    private TextField lastUpdatedField;

    @FXML
    public void addPart() {

        // Check for empty fields
        if (partCodeField.getText().trim().isEmpty() ||
                descriptionField.getText().trim().isEmpty() ||
                brandField.getText().trim().isEmpty() ||
                priceField.getText().trim().isEmpty() ||
                quantityField.getText().trim().isEmpty() ||
                lastUpdatedField.getText().trim().isEmpty() ||
                newCategoryComboBox.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all required fields.");
            alert.showAndWait();
            return;
        }

        try {

            Part part = new Part(
                    partCodeField.getText(),
                    descriptionField.getText(),
                    brandField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText()),
                    newCategoryComboBox.getValue(),
                    lastUpdatedField.getText(),
                    ""
            );

            partList.add(part);

            InventoryFileHandler.saveInventory(
                    "src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt",
                    partList
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Spare part added successfully.");
            alert.showAndWait();

            partCodeField.clear();
            descriptionField.clear();
            brandField.clear();
            priceField.clear();
            quantityField.clear();
            lastUpdatedField.clear();
            newCategoryComboBox.setValue(null);

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Price must be a decimal number and Quantity must be an integer.");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An unexpected error occurred.");
            alert.showAndWait();
        }
    }

    @FXML
    public void updatePart() {


        Part selectedPart=inventoryTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null){
            return;
        }

        selectedPart.setPartCode(partCodeField.getText());
        selectedPart.setName(descriptionField.getText());
        selectedPart.setBrand(brandField.getText());
        selectedPart.setPrice(Double.parseDouble(priceField.getText()));
        selectedPart.setQuantity(Integer.parseInt(quantityField.getText()));
        selectedPart.setCategory(newCategoryComboBox.getValue());
        selectedPart.setLastUpdated(lastUpdatedField.getText());

        inventoryTable.refresh();

        InventoryFileHandler.saveInventory(
                "src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt",
                partList
        );
    }

    @FXML
    public void deletePart() {
        Part selectedPart=inventoryTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null){
            return;
        }

        partList.remove(selectedPart);

        InventoryFileHandler.saveInventory(
                "src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt",
                partList
        );

    }

    @FXML
    public void openDealers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/malabespareparts/view/Dealer.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Dealer Management");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openPOSCheckout(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/malabespareparts/view/POSCheckout.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("POS Checkout");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showLowStock() {

        ObservableList<Part> lowStockList = FXCollections.observableArrayList();

        for (Part part : partList) {

            if (part.getQuantity() <= 5) {
                lowStockList.add(part);
            }
        }

        inventoryTable.setItems(lowStockList);
    }

    @FXML
    public void searchInventory() {

        String keyword = keywordField.getText().toLowerCase().trim();
        String category = categoryComboBox.getValue();

        double minPrice = 0;
        double maxPrice = Double.MAX_VALUE;

        try {
            if (!minPriceField.getText().isEmpty()) {
                minPrice = Double.parseDouble(minPriceField.getText());
            }

            if (!maxPriceField.getText().isEmpty()) {
                maxPrice = Double.parseDouble(maxPriceField.getText());
            }
        } catch (NumberFormatException e) {
            return;
        }

        ObservableList<Part> results = FXCollections.observableArrayList();

        for (Part part : partList) {

            boolean matchesKeyword =
                    keyword.isEmpty()
                            || part.getPartCode().toLowerCase().contains(keyword)
                            || part.getName().toLowerCase().contains(keyword)
                            || part.getBrand().toLowerCase().contains(keyword)
                            || part.getCategory().toLowerCase().contains(keyword);

            boolean matchesCategory =
                    category.equals("ALL")
                            || part.getCategory().equalsIgnoreCase(category);

            boolean matchesPrice =
                    part.getPrice() >= minPrice
                            && part.getPrice() <= maxPrice;

            if (matchesKeyword && matchesCategory && matchesPrice) {
                results.add(part);
            }
        }

        inventoryTable.setItems(results);
    }

    @FXML
    public void resetInventory() {

        keywordField.clear();
        minPriceField.clear();
        maxPriceField.clear();
        categoryComboBox.setValue("ALL");

        inventoryTable.setItems(partList);
    }

    @FXML
    public void exitApplication() {

        System.exit(0);

    }

    @FXML
    public void openAuditLog() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/malabespareparts/view/AuditLog.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Audit Log");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partCodeColumn.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        lastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));


        ArrayList<Part> parts =
                InventoryParser.loadInventory("src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt");

        partList = FXCollections.observableArrayList(parts);
        categoryComboBox.getItems().addAll(
                "ALL",
                "ENGINE",
                "BREAKS",
                "ELECTRICAL",
                "BODYWORK"
        );

        newCategoryComboBox.getItems().addAll(
                "ENGINE",
                "BRAKES",
                "ELECTRICAL",
                "BODYWORK"
        );
        categoryComboBox.setValue("ALL");

        FilteredList<Part> filteredList = new FilteredList<>(partList, p -> true);
        keywordField.textProperty().addListener((observable, oldValue, newValue) -> {
            String keyword = newValue.toLowerCase().trim();
            filteredList.setPredicate(part -> {
                String selectedCategory = categoryComboBox.getValue();

                boolean matchesKeyword = keyword.isEmpty()
                        || part.getPartCode().toLowerCase().contains(keyword)
                        || part.getName().toLowerCase().contains(keyword)
                        || part.getBrand().toLowerCase().contains(keyword)
                        || part.getCategory().toLowerCase().contains(keyword);

                boolean matchesCategory = selectedCategory.equals("ALL")
                        || part.getCategory().equalsIgnoreCase(selectedCategory);

                return matchesKeyword && matchesCategory;

            });
        });

        categoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            String keyword = keywordField.getText().toLowerCase().trim();

            filteredList.setPredicate(part -> {

                boolean matchesKeyword = keyword.isEmpty()
                        || part.getPartCode().toLowerCase().contains(keyword)
                        || part.getName().toLowerCase().contains(keyword)
                        || part.getBrand().toLowerCase().contains(keyword)
                        || part.getCategory().toLowerCase().contains(keyword);

                boolean matchesCategory = newValue.equals("ALL")
                        || part.getCategory().equalsIgnoreCase(newValue);

                return matchesKeyword && matchesCategory;
            });
        });

        inventoryTable.setItems(filteredList);

        inventoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedPart) -> {
            if (selectedPart !=null){
                partCodeField.setText(selectedPart.getPartCode());
                descriptionField.setText(selectedPart.getName());
                brandField.setText(selectedPart.getBrand());
                priceField.setText(String.valueOf(selectedPart.getPrice()));
                quantityField.setText(String.valueOf(selectedPart.getQuantity()));
                newCategoryComboBox.setValue(selectedPart.getCategory());
                lastUpdatedField.setText(selectedPart.getLastUpdated());
            }
        });

        minPriceField.textProperty().addListener((observable, oldValue, newValue) -> {

            String keyword = keywordField.getText().toLowerCase().trim();
            String selectedCategory = categoryComboBox.getValue();

            double minPrice = 0;

            if (!newValue.isEmpty()) {
                try {
                    minPrice = Double.parseDouble(newValue);
                } catch (NumberFormatException e) {
                    return;
                }
            }

            double maxPrice = Double.MAX_VALUE;

            if (!maxPriceField.getText().isEmpty()) {
                try {
                    maxPrice = Double.parseDouble(maxPriceField.getText());
                } catch (NumberFormatException e) {
                    return;
                }
            }

            double finalMinPrice = minPrice;
            double finalMaxPrice = maxPrice;

            filteredList.setPredicate(part -> {

                boolean matchesKeyword = keyword.isEmpty()
                        || part.getPartCode().toLowerCase().contains(keyword)
                        || part.getName().toLowerCase().contains(keyword)
                        || part.getBrand().toLowerCase().contains(keyword)
                        || part.getCategory().toLowerCase().contains(keyword);

                boolean matchesCategory = selectedCategory.equals("ALL")
                        || part.getCategory().equalsIgnoreCase(selectedCategory);

                boolean matchesPrice = part.getPrice() >= finalMinPrice
                        && part.getPrice() <= finalMaxPrice;

                return matchesKeyword && matchesCategory && matchesPrice;
            });
        });

        maxPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
            String keyword = keywordField.getText().toLowerCase().trim();
            String selectedCategory = categoryComboBox.getValue();

            double minPrice = 0;

            if (!minPriceField.getText().isEmpty()) {
                try {
                    minPrice = Double.parseDouble(minPriceField.getText());
                } catch (NumberFormatException e) {
                    return;
                }
            }

            double maxPrice = Double.MAX_VALUE;

            if (!newValue.isEmpty()) {
                try {
                    maxPrice = Double.parseDouble(newValue);
                } catch (NumberFormatException e) {
                    return;
                }
            }
            double finalMinPrice = minPrice;
            double finalMaxPrice = maxPrice;

            filteredList.setPredicate(part -> {
                boolean matchesKeyword = keyword.isEmpty()
                        || part.getPartCode().toLowerCase().contains(keyword)
                        || part.getName().toLowerCase().contains(keyword)
                        || part.getBrand().toLowerCase().contains(keyword)
                        || part.getCategory().toLowerCase().contains(keyword);

                boolean matchesCategory = selectedCategory.equals("ALL")
                        || part.getCategory().equalsIgnoreCase(selectedCategory);

                boolean matchesPrice = part.getPrice() >= finalMinPrice
                        && part.getPrice() <= finalMaxPrice;

                return matchesKeyword && matchesCategory && matchesPrice;
            });
        });

    }
}
