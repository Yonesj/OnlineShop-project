module com.example.firstproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.firstproject to javafx.fxml;
    opens model.user to javafx.fxml;
    opens model.connectors to javafx.fxml;
    opens model.commodity to javafx.fxml;

    exports model.commodity;
    exports com.example.firstproject;
    exports model.user;
    exports model.connectors;
}