package com.example.firstproject;

import control.CustomerControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.user.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPanelController implements Initializable{
//    private Stage stage;
//    private final LogginPanelController controller;
    public Customer customer;

//    public CustomerPanelController(LogginPanelController controller){
//        this.controller = controller;
//
//        this.stage = new Stage();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-panel.fxml"));
//
//            // Set this class as the controller
//            customer = controller.getCustomer();
//            loader.setController(this);
//
//
//            // Load the scene
//            stage.setScene(new Scene(loader.load()));
//
//            // Setup the window/stage
//            stage.setTitle("Passing Controllers Example - Layout2");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        stage.show();
//    }


    @FXML
    private Label customerNameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer = FirstPanelController.customer;
        customerNameLabel.setText(customer.getUsername());
    }
}
