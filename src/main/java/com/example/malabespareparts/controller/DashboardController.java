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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public  class  DashboardController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partCodeColumn.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        lastUpdatedColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdated"));


        ArrayList<Part> parts=
                InventoryParser.loadInventory("src/main/resources/com/example/malabespareparts/Data/inventory_legacy.txt");

        ObservableList<Part> list = FXCollections.observableArrayList(parts);

        inventoryTable.setItems(list);
    }
}
