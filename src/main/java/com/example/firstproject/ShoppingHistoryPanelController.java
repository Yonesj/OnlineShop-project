package com.example.firstproject;

import javafx.collections.ListChangeListener;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.commodity.Commodity;
import model.connectors.Invoice;
import model.user.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShoppingHistoryPanelController implements Initializable {
    private Customer customer;
    private Invoice invoice;
    @FXML
    private ImageView back_img;

    @FXML
    private TableView<Invoice> invoiceTable;
    @FXML
    private TableColumn<?, ?> invoiceColumn;
    @FXML
    private TableColumn<Invoice, String> invoiceIDcoulumn;
    @FXML
    private TableColumn<Invoice, LocalDate> dateColumn;
    @FXML
    private TableColumn<Invoice, Double> invoicePriceColumn;


    @FXML
    private TableView<Commodity> commodities;
    @FXML
    private TableColumn<Commodity, String> nameColumn;
    @FXML
    private TableColumn<Commodity, String> idColumn;
    @FXML
    private TableColumn<Commodity, Double> scoreColumn;
    @FXML
    private TableColumn<Commodity, Double> priceColumn;



    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("changing password page");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customer = FirstPanelController.customer;

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("aveScore"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        invoiceIDcoulumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        invoicePriceColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        invoiceTable.getItems().clear();
        for (Invoice invoice : customer.getShoppinHistory()){
            invoiceTable.getItems().add(invoice);
        }

        invoiceTable.setRowFactory(tv -> {
            TableRow<Invoice> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    invoice = row.getItem();
                    for (Commodity commodity : invoice.getCommodities()){
                        commodities.getItems().add(commodity);
                    }
                }
            });
            return row ;
        });



//        ObservableList<Invoice> selectedItem = invoiceTable.getSelectionModel().getSelectedItems();
//        selectedItem.addListener(new ListChangeListener<Invoice>() {
//            @Override
//            public void onChanged(Change<? extends Invoice> c) {
//                Invoice selectedInvoic = c.getAddedSubList().get(selectedItem.indexOf(c));
//                for (Commodity commodity: selectedInvoic.getCommodities()){
//                    commodities.getItems().add(commodity);
//                }
//            }
//        });

    }
}
