package com.example.firstproject;

import control.AdminControl;
import control.CustomerControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.connectors.Request;
import model.connectors.RequestType;
import model.user.Admin;
import model.user.Customer;
import model.user.User;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {
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

        String result = AdminControl.processCommand("help");
        helpResultLabel.setText(result);
        HelpscrollPane.setVisible(true);
        helpResultLabel.setVisible(true);
    }

    @FXML
    void logout_btn(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void sendCommand(MouseEvent event) {
        String command = inputTextfield.getText();
        String result = AdminControl.processCommand(command);
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

            indexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
            customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("requestType"));

            requestsTableView.getItems().clear();
            Admin admin = Admin.getAdmin();
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
    }
}
