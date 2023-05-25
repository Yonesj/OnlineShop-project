module com.example.firstproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.firstproject to javafx.fxml;
    exports com.example.firstproject;
}