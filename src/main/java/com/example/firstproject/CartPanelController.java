package com.example.firstproject;

import control.CustomerControl;
import exceptions.InsufficientBalanceException;
import exceptions.InsufficientStockException;
import exceptions.InvalidDiscountCodeException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.commodity.Commodity;
import model.user.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CartPanelController implements Initializable {
//    public static Stage previousStage;
    private Customer customer;
    private String discountCode = null;

    @FXML
    private ImageView back_img;

    @FXML
    private TableView<Commodity> cart;

    @FXML
    private TableColumn<Commodity, String> nameColumn;

    @FXML
    private TableColumn<Commodity, String> idColumn;

    @FXML
    private TableColumn<Commodity, Double> scoreColumn;

    @FXML
    private TableColumn<Commodity, Double> priceColumn;

    @FXML
    private TextField discountField;

    @FXML
    private Label priceinBox;


    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("cart page");
        stage.show();
    }

    @FXML
    void clear(MouseEvent event) {
        customer.clearCart();
        cart.getItems().clear();
    }

    @FXML
    void finalize(MouseEvent event) {
        try {
            CustomerControl.finalizePurchase(customer, discountCode);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("operation was successful");
            alert.show();
            cart.getItems().clear();
        } catch (InsufficientBalanceException | InsufficientStockException | InvalidDiscountCodeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    void addDiscount(MouseEvent event) {
        discountField.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer = FirstPanelController.customer;

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("aveScore"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        cart.getItems().clear();
        ObservableList<Commodity> data = FXCollections.observableArrayList(customer.getCart());
        cart.setItems(data);

        double finalprice = 0;
        for (Commodity commodity: customer.getCart()){
            finalprice += commodity.getPrice();
        }
        priceinBox.setText(Double.toString(finalprice));


        discountField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                discountCode = discountField.getText();
            }
        });
    }
}
