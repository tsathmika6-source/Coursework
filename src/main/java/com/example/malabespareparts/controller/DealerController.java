package com.example.malabespareparts.controller;

import com.example.malabespareparts.filehandler.DealerParser;
import com.example.malabespareparts.model.Dealer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Random;
import java.util.ArrayList;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DealerController implements Initializable {

    @FXML
    private TableView<Dealer> dealerTable;

    @FXML
    private TableColumn<Dealer, String> dealerIdColumn;

    @FXML
    private TableColumn<Dealer, String> dealerNameColumn;

    @FXML
    private TableColumn<Dealer, String> contactNumberColumn;

    @FXML
    private TableColumn<Dealer, String> addressColumn;

    private ObservableList<Dealer> dealerList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        dealerIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("dealerId"));

        dealerNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("dealerName"));

        contactNumberColumn.setCellValueFactory(
                new PropertyValueFactory<>("contactNumber"));

        addressColumn.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        ArrayList<Dealer> dealers =
                DealerParser.loadDealers(
                        "src/main/resources/com/example/malabespareparts/Data/dealers_legacy.txt"
                );


        dealerList = FXCollections.observableArrayList(dealers);

        dealerTable.setItems(dealerList);
    }
    @FXML
    public void selectRandomDealers() {
        ArrayList<Dealer> selectedDealers = new ArrayList<>();
        Random random = new Random();

        while (selectedDealers.size() < 4) {
            int index = random.nextInt(dealerList.size());

            Dealer dealer = dealerList.get(index);

            if (!selectedDealers.contains(dealer)) {
                selectedDealers.add(dealer);
            }
        }

        for (int i = 0; i < selectedDealers.size() - 1; i++) {
            for (int j = 0; j < selectedDealers.size() - i - 1; j++) {
                if (selectedDealers.get(j).getAddress()
                        .compareToIgnoreCase(selectedDealers.get(j + 1).getAddress()) > 0) {

                    Dealer temp = selectedDealers.get(j);
                    selectedDealers.set(j, selectedDealers.get(j + 1));
                    selectedDealers.set(j + 1, temp);
                }
            }
        }

        dealerTable.setItems(FXCollections.observableArrayList(selectedDealers));
    }
}


