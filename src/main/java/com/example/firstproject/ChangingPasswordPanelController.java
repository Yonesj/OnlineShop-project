package com.example.firstproject;

import exceptions.InvalidPasswordException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.user.Customer;

import java.io.IOException;
import java.util.Objects;

public class ChangingPasswordPanelController {
    @FXML
    private ImageView back_img;

    @FXML
    private TextField currentPassField;
    @FXML
    private TextField newPassField;
    @FXML
    private TextField reenterPassField;

    @FXML
    private Button inputInfo_btn;



    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("changing password page");
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    @FXML
    void inputInfo(MouseEvent event) {
        String currentPass = currentPassField.getText();
        String newPass = newPassField.getText();
        String reenter = reenterPassField.getText();
        Customer customer = FirstPanelController.customer;

        if(currentPass.equals(customer.getPassword())){
            try {
                customer.setPassword(newPass);

                Node source = (Node) event.getSource();
                Stage currentstage = (Stage) source.getScene().getWindow();
                currentstage.hide();

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("customer page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            } catch (InvalidPasswordException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("password is wrong!");
            alert.show();
        }
    }

}
