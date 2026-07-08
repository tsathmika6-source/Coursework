module com.example.malabespareparts {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.malabespareparts to javafx.fxml;
    exports com.example.malabespareparts;
}