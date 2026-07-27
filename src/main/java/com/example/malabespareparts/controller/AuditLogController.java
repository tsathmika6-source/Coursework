package com.example.malabespareparts.controller;

import com.example.malabespareparts.model.AuditLogEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class AuditLogController implements Initializable{

    @FXML
    private TableView<AuditLogEntry> auditTable;

    @FXML
    private TableColumn<AuditLogEntry, String> dateColumn;

    @FXML
    private TableColumn<AuditLogEntry, String> timeColumn;

    @FXML
    private TableColumn<AuditLogEntry, String> actionColumn;

    @FXML
    private TableColumn<AuditLogEntry, String> userColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        ObservableList<AuditLogEntry> logList =
                FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/com/example/malabespareparts/Data/audit_log.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 4) {
                    logList.add(new AuditLogEntry(
                            data[0],
                            data[1],
                            data[2],
                            data[3]
                    ));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        auditTable.setItems(logList);

    }

    }
