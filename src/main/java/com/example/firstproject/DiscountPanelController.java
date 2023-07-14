package com.example.firstproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.connectors.Discount;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class DiscountPanelController implements Initializable {
    @FXML
    private TableColumn<Discount, String> codeColumn;

    @FXML
    private TableColumn<Discount, LocalDate> dateColumn;

    @FXML
    private ScrollPane dicountScrollPane;

    @FXML
    private TableView<Discount> discountTable;

    @FXML
    private TableColumn<Discount, Double> percentColumn;

    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("customer page");
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        percentColumn.setCellValueFactory(new PropertyValueFactory<>("percent"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("expireDate"));

        discountTable.getItems().clear();
        ObservableList<Discount> data = FXCollections.observableArrayList(FirstPanelController.customer.getDiscounts());
        discountTable.setItems(data);
    }
}