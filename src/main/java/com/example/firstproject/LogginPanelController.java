package com.example.firstproject;


import control.AdminControl;
import control.CustomerControl;
import model.user.Customer;
import exceptions.InvalidPasswordException;
import exceptions.NotFound404Exception;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogginPanelController {
    private String username;
    private String password;
    private Customer customer;


    @FXML
    private TextField usernameInputTextField;
    @FXML
    private Label emptyUsername;
    @FXML
    private PasswordField password_txtfl;
    @FXML
    private Label emptyPassword;
    @FXML
    private Button inputLogginInfo_btn;
    @FXML
    private Button creatingAccount_btn;



    @FXML
    void passwordTextfield(KeyEvent event) {}

    @FXML
    void usernameInputTextField(KeyEvent event) {}

    @FXML
    void inputLogginInfo(MouseEvent event) throws IOException {
        username = usernameInputTextField.getText();
        password = password_txtfl.getText();

        if(username == null || username.equals("")){
            emptyUsername.setVisible(true);
        }else if(password == null || password.equals("")){
            emptyPassword.setVisible(true);
        }else {
            if(AdminControl.loggin(username,password)){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("admin page");
                stage.show();
            }else {
                try {
                    customer = CustomerControl.loggin(username,password);
                    FirstPanelController.customer = customer;
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.hide();

                }catch (NotFound404Exception | InvalidPasswordException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
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

    public Customer getCustomer() {
        return customer;
    }
}
