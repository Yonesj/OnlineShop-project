package com.example.firstproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FirstPanelController {

    @FXML
    private ImageView cartIcon;

    @FXML
    private TextField searchBar;

    @FXML
    private Button signIn_loggin_btn;

    @FXML
    void search(KeyEvent event) {

    }
    @FXML
    void cart_btn(MouseEvent event) {

    }

    @FXML
    void signIn_loggin_btn(MouseEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
        Scene scene1 = new Scene(root1,480,320);
        Stage stage1 = new Stage();
        stage1.setFullScreen(true);
        stage1.setScene(scene1);
        stage1.show();


        Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loggin-panel.fxml")));
        Scene scene = new Scene(root2);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign in page");
        stage.show();
    }
}