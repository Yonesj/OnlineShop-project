package com.example.firstproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.commodity.Commodity;
import model.user.Admin;
import model.user.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FirstPanelController implements Initializable {
    public static Commodity commodity;
    public static Customer customer;

    @FXML
    private ImageView cartIcon;
    @FXML
    private ImageView avatarIcon;

    @FXML
    private TextField searchBar;

    @FXML
    private ImageView search_img;

    @FXML
    private Button signIn_loggin_btn;

    @FXML
    void search(MouseEvent event) throws IOException {
        String id = searchBar.getText();
        Admin admin = Admin.getAdmin();
        boolean commodityFound = false;

        for (Commodity comm : admin.getCommodityList()){
            if(comm.getID().equals(id)){
                commodity = comm;
                commodityFound = true;
            }
        }

        if(commodityFound){
            Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
            Scene scene1 = new Scene(root1,480,320);
            Stage stage1 = new Stage();
            stage1.setFullScreen(true);
            stage1.setScene(scene1);
            stage1.show();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Product page");
            stage.show();
        }
    }

    @FXML
    void customerPanel(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Product page");
        stage.show();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(customer == null){
            avatarIcon.setVisible(false);
        }else {
            signIn_loggin_btn.setVisible(false);
            avatarIcon.setVisible(true);
        }

        searchBar.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                String id = searchBar.getText();
                Admin admin = Admin.getAdmin();
                boolean commodityFound = false;

                for (Commodity comm : admin.getCommodityList()){
                    if(comm.getID().equals(id)){
                        commodity = comm;
                        commodityFound = true;
                    }
                }

                if(commodityFound){
                    Parent root1 = null;
                    try {
                        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene1 = new Scene(root1,480,320);
                    Stage stage1 = new Stage();
                    stage1.setFullScreen(true);
                    stage1.setScene(scene1);
                    stage1.show();

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Product page");
                    stage.show();
                }
            }
        });
    }
}