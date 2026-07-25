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
        categoryComboBox.setValue("All");

        FilteredList<Part> filteredList = new FilteredList<>(partList, p -> true);
        keywordField.textProperty().addListener((observable, oldValue, newValue) -> {

            String keyword = newValue.toLowerCase().trim();
            filteredList.setPredicate(part -> {
                if (keyword.isEmpty()) {
                    return true;
                }

                return part.getPartCode().toLowerCase().contains(keyword)
                        || part.getName().toLowerCase().contains(keyword)
                        || part.getBrand().toLowerCase().contains(keyword)
                        || part.getCategory().toLowerCase().contains(keyword);
            });
        });
        inventoryTable.setItems(filteredList);
    }
}
