package com.example.malabespareparts.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class DashboardController {

    @FXML
    private TableView<?> inventoryTable;

    @FXML
    private TableColumn<?, ?> partCodeColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> brandColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> qtyColumn;

    @FXML
    private TableColumn<?, ?> categoryColumn;

    @FXML
    private TableColumn<?, ?> lastUpdatedColumn;
}
