package com.example.firstproject;

import control.CustomerControl;
import exceptions.InvalidEmailException;
import exceptions.InvalidPasswordException;
import exceptions.InvalidPhoneNumberException;
import exceptions.NotAvailableUsernameException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class SignUpPanelController {
    private String username;
    private String email;
    private String phone;
    private String password;

    @FXML
    private TextField usernameInputTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private PasswordField password_txtfl;

    @FXML
    private Button inputSigningInfo_btn;


    @FXML
    private Label usernameLabel;

    @FXML
    private Label emaillabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label passLabel;



    @FXML
    void usernameInputTextField(InputMethodEvent event) {

    }


    @FXML
    void emailTextField(KeyEvent event) {

    }

    @FXML
    void phoneTextField(KeyEvent event) {

    }

    @FXML
    void passwordTextfield(KeyEvent event) {

    }

    @FXML
    void inputSigningInfo_btn(MouseEvent event) {
        username = usernameInputTextField.getText();
        email = emailTextField.getText();
        phone = phoneTextField.getText();
        password = password_txtfl.getText();


        if(username == null || username.equals("")){
            usernameLabel.setVisible(true);
        }else if(email == null || email.equals("")){
            emaillabel.setText("this field can't be empty!");
            emaillabel.setVisible(true);
        }else if(phone == null || phone.equals("")){
            phoneLabel.setText("this field can't be empty!");
            phoneLabel.setVisible(true);
        }else if(password == null || password.equals("")){
            passLabel.setText("this field can't be empty!");
            passLabel.setVisible(true);
        }else {
            try {
                CustomerControl.signIn(username,email,phone,password);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("operation was successful");
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.hide();
            }catch (InvalidPhoneNumberException | InvalidEmailException | InvalidPasswordException | NotAvailableUsernameException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }
}