package com.example.firstproject;

import control.AdminControl;
import control.CustomerControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.connectors.Discount;
import model.connectors.Request;
import model.connectors.RequestType;
import model.user.Admin;
import model.user.Customer;
import model.user.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {
    private Admin admin = Admin.getAdmin();


    @FXML
    private Button openCommandPrompt;


    @FXML
    private MenuButton view_menubtn;
    @FXML
    private MenuItem viewUsers;
    @FXML
    private ScrollPane usersScrollPane;
    @FXML
    private TableView<Customer> usersTableView = new TableView<>();
    @FXML
    private TableColumn<Customer, String> usernameColumn;
    @FXML
    private TableColumn<Customer,String> phoneColumn;
    @FXML
    private TableColumn<Customer, Double> creditColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private MenuItem viewRequests;
    @FXML
    private ScrollPane requestsScrollPane;
    @FXML
    private TableView<Request> requestsTableView;
    @FXML
    private TableColumn<Request, Integer> indexColumn;

    @FXML
    private TableColumn<Request, Customer> customerColumn;
    @FXML
    private TableColumn<Request, RequestType> typeColumn;

    @FXML
    private MenuItem viewDiscounts;
    @FXML
    private ScrollPane dicountScrollPane;
    @FXML
    private TableView<Discount> discountTable;
    @FXML
    private TableColumn<Discount, String> codeColumn;
    @FXML
    private TableColumn<Discount, Double> percentColumn;
    @FXML
    private TableColumn<Discount, Integer> capacityColumn;
    @FXML
    private TableColumn<Discount, LocalDate> dateColumn;

    @FXML
    private Button helpCommand;
    @FXML
    private Button logout_btn;



    @FXML
    private ScrollPane HelpscrollPane;
    @FXML
    private Label helpResultLabel;


    @FXML
    private ScrollPane CommandScrollPane;
    @FXML
    private TextArea inputTextfield;
    @FXML
    private Button sendCommand;
    @FXML
    private Label commandResultLable;



    @FXML
    void openCommandPrompt(MouseEvent event) {
        requestsTableView.setVisible(false);
        requestsScrollPane.setVisible(false);
        usersTableView.setVisible(false);
        usersScrollPane.setVisible(false);
        helpResultLabel.setVisible(false);
        HelpscrollPane.setVisible(false);
        discountTable.setVisible(false);
        dicountScrollPane.setVisible(false);

        CommandScrollPane.setVisible(true);
        inputTextfield.setVisible(true);
        sendCommand.setVisible(true);
    }


    @FXML
    void helpCommand(MouseEvent event) {
        requestsTableView.setVisible(false);
        requestsScrollPane.setVisible(false);
        usersTableView.setVisible(false);
        usersScrollPane.setVisible(false);
        commandResultLable.setVisible(false);
        inputTextfield.setVisible(false);
        sendCommand.setVisible(false);
        CommandScrollPane.setVisible(false);
        discountTable.setVisible(false);
        dicountScrollPane.setVisible(false);

        String result = AdminControl.processCommand("help");
        helpResultLabel.setText(result);
        HelpscrollPane.setVisible(true);
        helpResultLabel.setVisible(true);
    }

    @FXML
    void logout_btn(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();

        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
        Scene scene1 = new Scene(root1, 480, 320);
        Stage stage1 = new Stage();
        stage1.setFullScreen(true);
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void sendCommand(MouseEvent event) {
        String[] commands = inputTextfield.getText().split("\n");
        String result = "";
        for (int i = 0; i < commands.length ; i++) {
            result = AdminControl.processCommand(commands[i]);
        }
        commandResultLable.setText(result);
        commandResultLable.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewUsers.setOnAction(event -> {
            requestsTableView.setVisible(false);
            requestsScrollPane.setVisible(false);
            helpResultLabel.setVisible(false);
            HelpscrollPane.setVisible(false);
            commandResultLable.setVisible(false);
            inputTextfield.setVisible(false);
            sendCommand.setVisible(false);
            CommandScrollPane.setVisible(false);
            discountTable.setVisible(false);
            dicountScrollPane.setVisible(false);

            usersScrollPane.setVisible(true);
            usersTableView.setVisible(true);

            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));

            usersTableView.getItems().clear();
            ObservableList<Customer> data = FXCollections.observableArrayList(CustomerControl.getCustomers());
            usersTableView.setItems(data);
        });

        viewRequests.setOnAction(event -> {
            usersTableView.setVisible(false);
            usersScrollPane.setVisible(false);
            helpResultLabel.setVisible(false);
            HelpscrollPane.setVisible(false);
            commandResultLable.setVisible(false);
            inputTextfield.setVisible(false);
            sendCommand.setVisible(false);
            CommandScrollPane.setVisible(false);
            discountTable.setVisible(false);
            dicountScrollPane.setVisible(false);

            requestsScrollPane.setVisible(true);
            requestsTableView.setVisible(true);

            indexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
            customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("requestType"));

            requestsTableView.getItems().clear();
            for (Request request: admin.getRequests()){
                if(request.getRequestType() == RequestType.COMMENT){
                    TableColumn<Request, String> commentColumn = new TableColumn<>("Comment");
                    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
                    requestsTableView.getColumns().add(commentColumn);

                    requestsTableView.getItems().add(request);
                }else {
                    TableColumn<Request, Double> amountColumn = new TableColumn<>("amount");
                    amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
                    requestsTableView.getColumns().add(amountColumn);

                    requestsTableView.getItems().add(request);
                }
            }
        });

        viewDiscounts.setOnAction(event -> {
            requestsTableView.setVisible(false);
            requestsScrollPane.setVisible(false);
            usersTableView.setVisible(false);
            usersScrollPane.setVisible(false);
            helpResultLabel.setVisible(false);
            HelpscrollPane.setVisible(false);
            commandResultLable.setVisible(false);
            inputTextfield.setVisible(false);
            sendCommand.setVisible(false);
            CommandScrollPane.setVisible(false);

            dicountScrollPane.setVisible(true);
            discountTable.setVisible(true);

            codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
            percentColumn.setCellValueFactory(new PropertyValueFactory<>("percent"));
            capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("expireDate"));

            discountTable.getItems().clear();
            ObservableList<Discount> data = FXCollections.observableArrayList(admin.getDiscounts());
            discountTable.setItems(data);
        });
    }
}
