package com.example.firstproject;

import control.CommodityControl;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.commodity.Commodity;
import model.commodity.SortBy;
import model.commodity.subclasses.PersonalComputer;
import model.user.Admin;
import model.user.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FirstPanelController implements Initializable {
    public static Commodity commodity;
    public static Customer customer;
    private static Admin admin = Admin.getAdmin();
    private static final CommodityControl commodityControl = new CommodityControl();
    private static int pageIndex = 1;
    private int pages = (int) Math.ceil(admin.getCommoditylistLen() / 9.0);
    private double maxPrice = 0;
    private double minPrice = 0;
    private float minScore = 0;

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
    private ImageView food_img;
    @FXML
    private ImageView digital_img;
    @FXML
    private ImageView Stationary_img;
    @FXML
    private ImageView vehicle_img;


    @FXML
    private Slider maxSlider;
    @FXML
    private Label maxValue;
    @FXML
    private Slider minSlider;
    @FXML
    private Label minValue;
    @FXML
    private Label scoreValue;
    @FXML
    private Slider scoreSlider;
    @FXML
    private CheckBox availabilityCheckBox;


    @FXML
    private Label alphabetSort;
    @FXML
    private Label cheapnessSort;
    @FXML
    private Label expensivnessSort;
    @FXML
    private Label defaultSort;
    @FXML
    private Label recentSort;
    @FXML
    private Label scoreSort;

    @FXML
    private GridPane gridPane = new GridPane();
    @FXML
    private AnchorPane anchor00;
    @FXML
    private AnchorPane anchor01;
    @FXML
    private AnchorPane anchor02;
    @FXML
    private AnchorPane anchor10;
    @FXML
    private AnchorPane anchor11;
    @FXML
    private AnchorPane anchor12;
    @FXML
    private AnchorPane anchor20;
    @FXML
    private AnchorPane anchor21;
    @FXML
    private AnchorPane anchor22;

    @FXML
    private ImageView next_img;

    @FXML
    private ImageView back_img;


    @FXML
    void search(MouseEvent event) throws IOException {
        String id = searchBar.getText();
        Admin admin = Admin.getAdmin();
        boolean commodityFound = false;

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                commodityFound = true;
            }
        }

        if (commodityFound) {
            Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
            Scene scene1 = new Scene(root1, 480, 320);
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
            stage.centerOnScreen();
            stage.setResizable(false);
        }
    }

    @FXML
    void signIn_loggin_btn(MouseEvent event) throws IOException {
        Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loggin-panel.fxml")));
        Scene scene = new Scene(root2);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign in page");
        stage.show();
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    @FXML
    void customerPanel(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("custom-panel.fxml")));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("customer page");
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    void cart_btn(MouseEvent event) throws IOException {
        if (customer != null) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cart-panel.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("cart page");
            stage.show();
            stage.setResizable(false);
            stage.centerOnScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you have to loggin first");
            alert.show();
        }
    }

    @FXML
    void stationaryCatrgory(MouseEvent event) throws IOException {
        commodityControl.setStationeryFilter(true);

        commodityControl.setVehicleFilter(false);
        commodityControl.setElectronicFilter(false);
        commodityControl.setFoodFilter(false);

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
    void vehicleCategory(MouseEvent event) throws IOException {
        commodityControl.setVehicleFilter(true);

        commodityControl.setStationeryFilter(false);
        commodityControl.setElectronicFilter(false);
        commodityControl.setFoodFilter(false);

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
    void digitalCategory(MouseEvent event) throws IOException {
        commodityControl.setElectronicFilter(true);

        commodityControl.setStationeryFilter(false);
        commodityControl.setVehicleFilter(false);
        commodityControl.setFoodFilter(false);

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
    void foodCategory(MouseEvent event) throws IOException {
        commodityControl.setFoodFilter(true);

        commodityControl.setStationeryFilter(false);
        commodityControl.setVehicleFilter(false);
        commodityControl.setElectronicFilter(false);

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
    void sortByAlphabet(MouseEvent event) throws IOException {
        Commodity.setSortBy(SortBy.ALPHABET);

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
    void sortByCheapness(MouseEvent event) throws IOException {
        Commodity.setSortBy(SortBy.CHEAPNESS);

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
    void sortByDefault(MouseEvent event) throws IOException {
        Commodity.setSortBy(SortBy.DEFAULT);

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
    void sortByExpensivness(MouseEvent event) throws IOException {
        Commodity.setSortBy(SortBy.EXPENSIVENESS);

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
    void sortByRecent(MouseEvent event) throws IOException {
        Commodity.setSortBy(SortBy.RECENT);

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
    void sortByScore(MouseEvent event) throws IOException {
        Commodity.setSortBy(SortBy.SCORE);

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
    void apply(MouseEvent event) throws IOException {
        commodityControl.setPriceDOWNlimit(minPrice);
        commodityControl.setPriceUPlimit(maxPrice);
        commodityControl.setScoreLimit(minScore);

        if(availabilityCheckBox.isSelected()){
            commodityControl.setAvaliableFilter(true);
        }

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
    void reset(MouseEvent event) throws IOException {
        minPrice = 0;
        maxPrice = 0;
        minScore = 0;
        availabilityCheckBox.setSelected(false);

        apply(event);
    }

    @FXML
    void goToRelatedPanel00(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor00.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }

    @FXML
    void goToRelatedPanel01(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor01.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }

    @FXML
    void goToRelatedPanel02(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor02.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }

    @FXML
    void goToRelatedPanel10(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor10.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }

    @FXML
    void goToRelatedPanel11(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor11.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }

    @FXML
    void goToRelatedPanel12(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor12.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }

    @FXML
    void goToRelatedPanel20(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor20.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }

    @FXML
    void goToRelatedPanel21(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor21.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }

    @FXML
    void goToRelatedPanel22(MouseEvent event) throws IOException {
        Label idLabel = (Label) anchor22.getChildren().get(0);
        String id = idLabel.getText();

        for (Commodity comm : admin.getCommodityList()) {
            if (comm.getID().equals(id)) {
                commodity = comm;
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("commodity-panel.fxml")));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Product page");
                stage.show();
                stage.setResizable(false);
                stage.centerOnScreen();
            }
        }
    }


    @FXML
    void next(MouseEvent event) throws IOException {
        pageIndex++;
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
        Scene scene1 = new Scene(root1, 480, 320);
        Stage stage1 = new Stage();
        stage1.setFullScreen(true);
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void back(MouseEvent event) throws IOException {
        pageIndex--;
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
        Scene scene1 = new Scene(root1, 480, 320);
        Stage stage1 = new Stage();
        stage1.setFullScreen(true);
        stage1.setScene(scene1);
        stage1.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (customer == null) {
            avatarIcon.setVisible(false);
        } else {
            signIn_loggin_btn.setVisible(false);
            avatarIcon.setVisible(true);
        }

        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String id = searchBar.getText();
                boolean commodityFound = false;

                for (Commodity comm : admin.getCommodityList()) {
                    if (comm.getID().equals(id)) {
                        commodity = comm;
                        commodityFound = true;
                    }
                }

                if (commodityFound) {
                    Parent root1 = null;
                    try {
                        root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-panel.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene1 = new Scene(root1, 480, 320);
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

        AnchorPane[] anchorPanes = new AnchorPane[9];
        anchorPanes[0] = anchor00;
        anchorPanes[1] = anchor01;
        anchorPanes[2] = anchor02;
        anchorPanes[3] = anchor10;
        anchorPanes[4] = anchor11;
        anchorPanes[5] = anchor12;
        anchorPanes[6] = anchor20;
        anchorPanes[7] = anchor21;
        anchorPanes[8] = anchor22;

        Commodity[] page = new Commodity[9];
        int commodityIndex = (pageIndex - 1) * 9;


        commodityControl.fillSeed();

        //filters
        if(commodityControl.getSeed() != null) {
            for (Commodity commodity1 : commodityControl.getSeed()) {
                if(commodity1.getPrice() > maxPrice){
                    maxPrice = commodity1.getPrice();
                }
            }
        }

        minSlider.setMin(0);
        minSlider.setMax(maxPrice);
        minSlider.setValue(0);

        maxSlider.setMin(minSlider.getValue());
        maxSlider.setMax(maxPrice);
        maxSlider.setValue(maxPrice);

        scoreSlider.setMin(0);
        scoreSlider.setMax(5);
        scoreSlider.setValue(0);

        maxValue.setText(Double.toString(maxSlider.getValue()));
        minValue.setText(Double.toString(minSlider.getValue()));
        scoreValue.setText(Double.toString(scoreSlider.getValue()));

        for (int i = 0; i < 9; i++) {
            if ((commodityControl.getSeed(commodityIndex)) != null) {
                page[i] = commodityControl.getSeed(commodityIndex);
                commodityIndex++;
            }
        }

        //sort
        if(Commodity.getSortBy() == SortBy.DEFAULT){
            defaultSort.setTextFill(Color.web("#dc143c"));
        }else if(Commodity.getSortBy() == SortBy.CHEAPNESS){
            cheapnessSort.setTextFill(Color.web("#dc143c"));
        }if(Commodity.getSortBy() == SortBy.EXPENSIVENESS){
            expensivnessSort.setTextFill(Color.web("#dc143c"));
        }else if(Commodity.getSortBy() == SortBy.ALPHABET){
            alphabetSort.setTextFill(Color.web("#dc143c"));
        }else if(Commodity.getSortBy() == SortBy.RECENT){
            recentSort.setTextFill(Color.web("#dc143c"));
        }else if(Commodity.getSortBy() == SortBy.SCORE){
            scoreSort.setTextFill(Color.web("#dc143c"));
        }

        for (int i = 0; i < 9; i++){
            if(page[i] != null) {
                Label id = (Label) anchorPanes[i].getChildren().get(0);
                id.setText(page[i].getID());

                ImageView imageView = (ImageView) anchorPanes[i].getChildren().get(1);
                imageView.setImage(page[i].getImage());

                Label name = (Label) anchorPanes[i].getChildren().get(2);
                name.setText(page[i].getName());

                Label score = (Label) anchorPanes[i].getChildren().get(3);
                score.setText(Double.toString(page[i].getAveScore()));

                Label price = (Label) anchorPanes[i].getChildren().get(4);
                price.setText(Double.toString(page[i].getPrice()));
            }else {
                anchorPanes[i].setVisible(false);
            }
        }

        gridPane.setVisible(true);
        gridPane.setHgap(40);
        gridPane.setVgap(30);

        if(pageIndex == 1){
            back_img.setVisible(false);
        }
        if(pageIndex >= pages){
            next_img.setVisible(false);
        }


        scoreSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                scoreValue.setText(Float.toString((Float) newValue));
                minScore = (Float) newValue;
            }
        });

        minSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                minValue.setText(Double.toString((Double) newValue));
                minPrice = (Double) newValue;
                maxSlider.setMin((Double) newValue);
            }
        });

        maxSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                maxValue.setText(Double.toString((Double) newValue));
                maxPrice = (Double) newValue;
                minSlider.setMax((Double) newValue);
            }
        });


        ScaleTransition maximize = new ScaleTransition();
        maximize.setByX(.1);
        maximize.setByY(.1);
        maximize.setAutoReverse(false);
        maximize.setCycleCount(1);
        maximize.setDuration(Duration.millis(200));

        ScaleTransition minimize = new ScaleTransition();
        minimize.setByY(-0.1);
        minimize.setByX(-0.1);
        minimize.setAutoReverse(false);
        minimize.setCycleCount(1);
        minimize.setDuration(Duration.millis(200));

        DropShadow shadow = new DropShadow();

        signIn_loggin_btn.setOnMouseEntered(event -> {
            signIn_loggin_btn.setEffect(shadow);
            maximize.setNode(signIn_loggin_btn);
            maximize.play();
        });

        signIn_loggin_btn.setOnMouseExited(event -> {
            signIn_loggin_btn.setEffect(null);
            minimize.setNode(signIn_loggin_btn);
            minimize.play();
        });

        anchor00.setOnMouseEntered(event -> {
            maximize.setNode(anchor00);
            maximize.play();
        });

        anchor01.setOnMouseEntered(event -> {
            maximize.setNode(anchor01);
            maximize.play();
        });

        anchor02.setOnMouseEntered(event -> {
            maximize.setNode(anchor02);
            maximize.play();
        });

        anchor10.setOnMouseEntered(event -> {
            maximize.setNode(anchor10);
            maximize.play();
        });

        anchor11.setOnMouseEntered(event -> {
            maximize.setNode(anchor11);
            maximize.play();
        });

        anchor12.setOnMouseEntered(event -> {
            maximize.setNode(anchor12);
            maximize.play();
        });

        anchor20.setOnMouseEntered(event -> {
            maximize.setNode(anchor20);
            maximize.play();
        });

        anchor21.setOnMouseEntered(event -> {
            maximize.setNode(anchor21);
            maximize.play();
        });

        anchor22.setOnMouseEntered(event -> {
            maximize.setNode(anchor22);
            maximize.play();
        });


        anchor00.setOnMouseExited(event -> {
            minimize.setNode(anchor00);
            minimize.play();
        });

        anchor01.setOnMouseExited(event -> {
            minimize.setNode(anchor01);
            minimize.play();
        });

        anchor02.setOnMouseExited(event -> {
            minimize.setNode(anchor02);
            minimize.play();
        });

        anchor10.setOnMouseExited(event -> {
            minimize.setNode(anchor10);
            minimize.play();
        });

        anchor11.setOnMouseExited(event -> {
            minimize.setNode(anchor11);
            minimize.play();
        });

        anchor12.setOnMouseExited(event -> {
            minimize.setNode(anchor12);
            minimize.play();
        });

        anchor20.setOnMouseExited(event -> {
            minimize.setNode(anchor20);
            minimize.play();
        });

        anchor21.setOnMouseExited(event -> {
            minimize.setNode(anchor21);
            minimize.play();
        });

        anchor22.setOnMouseExited(event -> {
            minimize.setNode(anchor22);
            minimize.play();
        });

        food_img.setOnMouseEntered(event -> {
            maximize.setNode(food_img);
            maximize.play();
        });
        food_img.setOnMouseExited(event -> {
            minimize.setNode(food_img);
            minimize.play();
        });

        vehicle_img.setOnMouseEntered(event -> {
            maximize.setNode(vehicle_img);
            maximize.play();
        });
        vehicle_img.setOnMouseExited(event -> {
            minimize.setNode(vehicle_img);
            minimize.play();
        });

        Stationary_img.setOnMouseEntered(event -> {
            maximize.setNode(Stationary_img);
            maximize.play();
        });
        Stationary_img.setOnMouseExited(event -> {
            minimize.setNode(Stationary_img);
            minimize.play();
        });

        digital_img.setOnMouseEntered(event -> {
            maximize.setNode(digital_img);
            maximize.play();
        });
        digital_img.setOnMouseExited(event -> {
            minimize.setNode(digital_img);
            minimize.play();
        });
    }
}