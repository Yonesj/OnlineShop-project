package com.example.firstproject;


import control.AdminControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogginPanelController {
    private String username;
    private String password;

    @FXML
    private Button creatingAccount_btn;

    @FXML
    private Label emptyPassword;

    @FXML
    private Label emptyUsername;

    @FXML
    private Button inputLogginInfo_btn;

    @FXML
    private PasswordField password_txtfl;

    @FXML
    private TextField usernameInputTextField;

    @FXML
    void passwordTextfield(KeyEvent event) {
        password = event.getText();
    }

    @FXML
    void usernameInputTextField(KeyEvent event) {
        username = event.getText();
    }

    @FXML
    void inputLogginInfo(MouseEvent event) {
        if(username == null){
            emptyUsername.setVisible(true);
        }else if(password == null){
            emptyPassword.setVisible(true);
        }else {
            AdminControl.loggin(username,password);
        }

    }

    @FXML
    void creatingAccount_btn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign up page");
        stage.show();
    }
}
