module com.example.malabespareparts {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.malabespareparts to javafx.fxml;
    opens com.example.malabespareparts.controller to javafx.fxml;

    exports com.example.malabespareparts;
    exports com.example.malabespareparts.controller;
}