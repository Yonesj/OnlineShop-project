package com.example.firstproject;

import exceptions.InvalidEmailException;
import exceptions.InvalidPhoneNumberException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.user.Customer;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomerPanelController implements Initializable{
    public Customer customer;

    @FXML
    private ImageView back_img;
    @FXML
    private Label username;

    @FXML
    private Label usernameInBox;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label creditLabel;

    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;


    @FXML
    void editInfo(MouseEvent event) {
        if(emailField.isVisible()){
            emailField.setVisible(false);
            phoneField.setVisible(false);
        }else {
            emailField.setVisible(true);
            phoneField.setVisible(true);
        }
    }

    @FXML
    void cart_btn(MouseEvent event) throws IOException {
//        Node source = (Node) event.getSource();
//        Stage currentStage = (Stage) source.getScene().getWindow();
//        CartPanelController.previousStage = currentStage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cart-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("cart page");
        stage.show();
    }

    @FXML
    void increaseCredot_btn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("increaseCredit-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("changing password page");
        stage.show();
    }

    @FXML
    void shoppingHistory_btn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shoppingHistory-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("history page");
        stage.show();
    }

    @FXML
    void changePass_btn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("changePass-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("changing password page");
        stage.show();
    }

    @FXML
    void logout_btn(MouseEvent event) throws IOException {
        FirstPanelController.customer = null;
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
        Scene scene1 = new Scene(root1,480,320);
        Stage stage1 = new Stage();
        stage1.setFullScreen(true);
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
        Scene scene1 = new Scene(root1,480,320);
        Stage stage1 = new Stage();
        stage1.setFullScreen(true);
        stage1.setScene(scene1);
        stage1.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer = FirstPanelController.customer;

        username.setText(customer.getUsername());
        usernameInBox.setText(customer.getUsername());
        emailLabel.setText(customer.getEmailAddress());
        phoneLabel.setText(customer.getPhoneNumber());
        creditLabel.setText(Double.toString( customer.getCredit()));

        emailField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                try {
                    customer.setEmailAddress(emailField.getText());
                    emailLabel.setText(customer.getEmailAddress());
                    emailField.setVisible(false);
                } catch (InvalidEmailException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        phoneField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                try {
                    customer.setPhoneNumber(phoneField.getText());
                    emailLabel.setText(customer.getPhoneNumber());
                    phoneField.setVisible(false);
                } catch (InvalidPhoneNumberException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });
    }
}
