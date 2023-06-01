package com.example.firstproject;

import control.CustomerControl;
import exceptions.InvalidCardException;
import exceptions.InvalidCvv2Exception;
import exceptions.InvalidPurchaseException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PurchasePanelController {

    @FXML
    private ImageView back_img;

    @FXML
    private TextField cardField;

    @FXML
    private TextField cvv2Field;

    @FXML
    private TextField dateField;

    @FXML
    private TextField passField;

    @FXML
    private TextField amount;

    @FXML
    private Button pay_btn;

    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("changing password page");
        stage.show();
    }

    @FXML
    void pay(MouseEvent event) {
        String card = cardField.getText();
        String cvv2 = cvv2Field.getText();
        double credit = Double.parseDouble(amount.getText());

        try {
            CustomerControl.increaseCreditReq(FirstPanelController.customer,card,cvv2,credit);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("your request has been sent to admin");
            alert.show();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom-panel.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("changing password page");
            stage.show();
        } catch (InvalidCardException | InvalidCvv2Exception | InvalidPurchaseException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }


    }

}
