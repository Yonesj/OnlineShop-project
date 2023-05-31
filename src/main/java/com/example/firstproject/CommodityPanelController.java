package com.example.firstproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.commodity.*;
import model.commodity.subclasses.Electronic;
import model.commodity.subclasses.Pen;
import model.commodity.subclasses.Pencil;
import model.connectors.*;
import model.user.Admin;
import model.user.Customer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class CommodityPanelController implements Initializable {
    private Commodity commodity;
    private Customer customer;
    private int counts = 0;

    @FXML
    private Label commodityName;
    @FXML
    private Pane pane = new Pane();
    @FXML
    private ImageView commodityImage;
    @FXML
    private Label scoreLabel;
    @FXML
    private Text primaryPrice;
    @FXML
    private Label secondaryPrice;
    @FXML
    private Label id;
    @FXML
    private Label availability;


    @FXML
    private Label priceinBox;

    @FXML
    private Button addToCart_btn;
    @FXML
    private Rectangle shopBox;
    @FXML
    private Label shopCommand = new Label("how many item you wanna purchase?");
    @FXML
    private Spinner<Integer> itemSpinner;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelBtn;

    @FXML
    private Button comment_btn;
    @FXML
    private Button commentRequest;
    @FXML
    private Rectangle commentBox;
    @FXML
    private TextArea commentTextfield;

    @FXML
    private Button score_btn;
    @FXML
    private Rectangle scoreBox;
    @FXML
    private Label scorePrompt;
    @FXML
    private Button scoreSubmit;
    @FXML
    private TextField scoreTextfield;

    @FXML
    private Button back_btn;

    @FXML
    private TableView<Comment> commentTable;
    @FXML
    private TableColumn<Comment, String> userColumn = new TableColumn<>();
    @FXML
    private TableColumn<Comment, String> isbuyedColumn = new TableColumn<>();
    @FXML
    private TableColumn<Comment, String> commentColumn = new TableColumn<>();


    @FXML
    void addToCart(MouseEvent event) {
        if(customer != null){
            commentTable.setVisible(false);

            scoreSubmit.setVisible(false);
            scoreTextfield.setVisible(false);
            scorePrompt.setVisible(false);
            scoreBox.setVisible(false);

            commentRequest.setVisible(false);
            commentTextfield.setVisible(false);
            commentBox.setVisible(false);

            shopBox.setVisible(true);
            shopCommand.setVisible(true);
            itemSpinner.setVisible(true);
            cancelBtn.setVisible(true);
            addButton.setVisible(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you have to loggin first");
            alert.show();
        }
    }

    @FXML
    void add(MouseEvent event) {
        for (int i = 0; i < counts ; i++) {
            customer.getCart().add(commodity);
        }

        addButton.setVisible(false);
        cancelBtn.setVisible(false);
        itemSpinner.setVisible(false);
        shopCommand.setVisible(false);
        shopBox.setVisible(false);

        commentTable.setVisible(true);
    }
    @FXML
    void cancelShop(MouseEvent event) {
        addButton.setVisible(false);
        cancelBtn.setVisible(false);
        itemSpinner.setVisible(false);
        shopBox.setVisible(false);

        commentTable.setVisible(true);
    }

    @FXML
    void comment(MouseEvent event) {
        if(customer != null){
            commentTable.setVisible(false);

            scoreSubmit.setVisible(false);
            scoreTextfield.setVisible(false);
            scorePrompt.setVisible(false);
            scoreBox.setVisible(false);

            addButton.setVisible(false);
            cancelBtn.setVisible(false);
            itemSpinner.setVisible(false);
            shopCommand.setVisible(false);
            shopBox.setVisible(false);

            commentBox.setVisible(true);
            commentTextfield.setVisible(true);
            commentRequest.setVisible(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you have to loggin first");
            alert.show();
        }
    }

    @FXML
    void commentRequest(MouseEvent event) {
        String text = commentTextfield.getText();
        boolean isBuyes = false;

        for (Invoice invoice: customer.getShoppinHistory()){
            for (Commodity commodity1 : invoice.getCommodities()){
                if(commodity.getID().equals(commodity1.getID())){
                    isBuyes = true;
                    break;
                }
            }
        }

        if(text != null || !text.equals("")){
            Comment comment = new Comment(customer,commodity.getID(),text,isBuyes,Status.WAITING);
            Request request = new Request(customer,comment,commodity);
            Admin admin = Admin.getAdmin();
            admin.addRequest(request);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("your request has been sent to the admin!");
            alert.show();

            commentRequest.setVisible(false);
            commentTextfield.setVisible(false);
            commentBox.setVisible(false);

            commentTable.setVisible(true);
        }
    }

    @FXML
    void score(MouseEvent event) {
        if(customer != null){
            boolean isBuyes = false;

            for (Invoice invoice: customer.getShoppinHistory()){
                for (Commodity commodity1 : invoice.getCommodities()){
                    if(commodity.getID().equals(commodity1.getID())){
                        isBuyes = true;
                        break;
                    }
                }
            }

            if(isBuyes){
                commentTable.setVisible(false);

                commentRequest.setVisible(false);
                commentTextfield.setVisible(false);
                commentBox.setVisible(false);

                addButton.setVisible(false);
                cancelBtn.setVisible(false);
                itemSpinner.setVisible(false);
                shopCommand.setVisible(false);
                shopBox.setVisible(false);

                scoreBox.setVisible(true);
                scorePrompt.setVisible(true);
                scoreTextfield.setVisible(true);
                scoreSubmit.setVisible(true);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you have to buy this product first :)");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you have to loggin first");
            alert.show();
        }

    }

    @FXML
    void scoreSubmit(MouseEvent event) {
        float inputScore = Float.parseFloat(scoreTextfield.getText());
        Score score = new Score(customer,inputScore,commodity);
        commodity.addScore(score);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("your score submitted");
        alert.show();

        scoreSubmit.setVisible(false);
        scoreTextfield.setVisible(false);
        scorePrompt.setVisible(false);
        scoreBox.setVisible(false);

        commentTable.setVisible(true);
    }

    @FXML
    void back(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commodity = FirstPanelController.commodity;
        customer = FirstPanelController.customer;

        commodityName.setText(commodity.getName());
        scoreLabel.setText(Double.toString(commodity.getAveScore()));
        primaryPrice.setText(Double.toString(commodity.getPrice()));
        priceinBox.setText(Double.toString(commodity.getPrice()));

        if(commodity instanceof Pen) {
            Pen pen = (Pen) commodity;
            if (pen.getPercent() > 0) {
                double discounted = pen.getPrice() - (pen.getPercent() * pen.getPrice() / 100.0);
                primaryPrice.setStrikethrough(true);
                secondaryPrice.setText(Double.toString(discounted));
                priceinBox.setText(Double.toString(discounted));
            } else {
                secondaryPrice.setVisible(false);
            }
        }else if(commodity instanceof Pencil){
            Pencil pencil = (Pencil) commodity;
            if (pencil.getPercent() > 0) {
                double discounted = pencil.getPrice() - (pencil.getPercent() * pencil.getPrice() / 100.0);
                primaryPrice.setStrikethrough(true);
                secondaryPrice.setText(Double.toString(discounted));
                priceinBox.setText(Double.toString(discounted));
            } else {
                secondaryPrice.setVisible(false);
            }
        }else if(commodity instanceof Electronic){
            Electronic electronic = (Electronic) commodity;
            if (electronic.getPercent() > 0) {
                double discounted = electronic.getPrice() - (electronic.getPercent() * electronic.getPrice() / 100.0);
                primaryPrice.setStrikethrough(true);
                secondaryPrice.setText(Double.toString(discounted));
                priceinBox.setText(Double.toString(discounted));
            } else {
                secondaryPrice.setVisible(false);
            }
        } else {
            secondaryPrice.setVisible(false);
        }

        id.setText(commodity.getID());

        if(commodity.getStock() > 0){
            availability.setText("In Stock");
        }else {
            availability.setText("Out of Stock");
        }

        commodityImage = new ImageView(commodity.getImage());
        pane.setMaxSize(250,250);
        pane.setPrefSize(250,250);
        pane.getChildren().add(commodityImage);
        commodityImage.fitWidthProperty().bind(pane.widthProperty());
        commodityImage.fitHeightProperty().bind(pane.heightProperty());


        userColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        isbuyedColumn.setCellValueFactory(new PropertyValueFactory<>("isBuyed"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("text"));

        if(commodity.getComments() != null) {
            commentTable.getItems().clear();

            for (Comment comment : commodity.getComments()) {
                if (comment.getStatus() == Status.ACCEPTED) {
                    commentTable.getItems().add(comment);
                }
            }
        }

        commentTable.setVisible(true);

        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000,0);
        itemSpinner.setValueFactory(spinnerValueFactory);
        itemSpinner.setEditable(true);
        itemSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                counts = itemSpinner.getValue();
            }
        });
    }
}
