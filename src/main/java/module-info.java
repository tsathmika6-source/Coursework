module com.example.malabespareparts {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.malabespareparts to javafx.fxml;
    opens com.example.malabespareparts.controller to javafx.fxml;

    opens com.example.malabespareparts.model to javafx.base;

    exports com.example.malabespareparts;
    exports com.example.malabespareparts.controller;
    exports com.example.malabespareparts.model;
}