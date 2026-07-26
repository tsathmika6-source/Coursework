package com.example.malabespareparts.controller;

import com.example.malabespareparts.model.CartItem;
import com.example.malabespareparts.model.Part;
import com.example.malabespareparts.filehandler.InventoryParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.malabespareparts.filehandler.InventoryFileHandler;
import com.example.malabespareparts.controller.AuditLogFileHandler;
import com.example.malabespareparts.model.AuditLogEntry;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class POSCheckoutController implements Initializable {

    @FXML
    private TableView<Part> inventoryTable;

    @FXML
    private TableColumn<Part, String> partCodeColumn;

    @FXML
    private TableColumn<Part, String> nameColumn;

    @FXML
    private TableColumn<Part, Double> priceColumn;

    @FXML
    private TableColumn<Part, Integer> stockColumn;

    @FXML
    private TableColumn<Part, String> categoryColumn;

    @FXML
    private TableView<CartItem> cartTable;

    @FXML
    private TableColumn<CartItem, String> cartNameColumn;

    @FXML
    private TableColumn<CartItem, Integer> cartQuantityColumn;

    @FXML
    private TableColumn<CartItem, Double> cartSubtotalColumn;

    @FXML
    private TextField quantityField;

    @FXML
    private Label totalLabel;

    private ObservableList<Part> partList;
    private ObservableList<CartItem> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partCodeColumn.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        cartNameColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getPart().getName()));

        cartQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cartSubtotalColumn.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        ArrayList<Part> parts = InventoryParser.loadInventory(
                "src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt"
        );

        partList = FXCollections.observableArrayList(parts);

        inventoryTable.setItems(partList);
        cartTable.setItems(cartList);
    }
    @FXML
    public void addToCart() {
        Part selectedPart = inventoryTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            new Alert(Alert.AlertType.WARNING,
                    "Please select a part.").showAndWait();
            return;
        }


        int quantity;

        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Quantity must be a number.").showAndWait();
            return;
        }

        if (quantity <= 0) {
            new Alert(Alert.AlertType.WARNING,
                    "Quantity must be greater than zero.").showAndWait();
            return;
        }

        if (quantity > selectedPart.getQuantity()) {
            new Alert(Alert.AlertType.WARNING,
                    "Not enough stock available.").showAndWait();
            return;
        }

        CartItem item = new CartItem(selectedPart, quantity);

        cartList.add(item);

        updateTotal();

        quantityField.clear();
    }

    private void updateTotal() {

        double total = calculateFinalTotal();

        totalLabel.setText(String.format("Total : Rs. %.2f", total));
    }

    private double calculateFinalTotal() {
        double total = 0;

        boolean hasEngine = false;
        boolean hasElectrical = false;

        for (CartItem item : cartList) {

            double subtotal = item.getSubtotal();
            if (item.getQuantity() >= 3) {
                subtotal = subtotal * 0.95;
            }
            total += subtotal;

            if (item.getPart().getCategory().equalsIgnoreCase("ENGINE")) {
                hasEngine = true;
            }

            if (item.getPart().getCategory().equalsIgnoreCase("ELECTRICAL")) {
                hasElectrical = true;
            }
        }

        if (hasEngine && hasElectrical) {
            total = total * 0.90;
        }

        return total;
    }

    private void deductStock() {
        for (CartItem item : cartList) {
            Part part = item.getPart();

            part.setQuantity(
                    part.getQuantity() - item.getQuantity()
            );
        }

        inventoryTable.refresh();
    }

    private void writeAuditLog() {
        for (CartItem item : cartList) {
            AuditLogEntry log = new AuditLogEntry(
                    LocalDate.now().toString(),
                    LocalTime.now().withNano(0).toString(),
                    "CHECKOUT : " + item.getPart().getPartCode()
                            + " Qty=" + item.getQuantity(),
                    "Admin"
            );

            AuditLogFileHandler.writeLog(
                    "src/main/resources/com/example/malabespareparts/Data/audit_log.txt",
                    log
            );
    }
}

        @FXML
    public void checkout() {
            if (cartList.isEmpty()) {
                new Alert(Alert.AlertType.WARNING,
                        "Cart is empty.").showAndWait();
                return;
            }

            double finalTotal = calculateFinalTotal();

            deductStock();

            InventoryFileHandler.saveInventory(
                    "src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt",
                    partList
            );

            writeAuditLog();

            cartList.clear();

            inventoryTable.refresh();

            cartTable.refresh();

            totalLabel.setText("Total : Rs. 0.00");

            new  Alert(
                    Alert.AlertType.INFORMATION,
                    String.format(
                            "Checkout Successful!\n\nFinal Total : Rs. %.2f",
                            finalTotal)
            ).showAndWait();

    }
}