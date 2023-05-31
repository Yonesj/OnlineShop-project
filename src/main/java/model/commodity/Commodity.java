package model.commodity;

import javafx.scene.image.Image;
import model.connectors.Comment;
import model.connectors.Score;
import model.user.Admin;

import java.util.ArrayList;

public abstract class Commodity implements Comparable{
    // instance variables
    private String ID;
    private String name;
    private double price;
    private int stock;
    private double aveScore;
    private ArrayList<Comment> comments;
    private ArrayList<Score> scores;
    private Category category;
    private Image image;
    private static SortBy sortBy = SortBy.DEFAULT;

    //constructors
    public Commodity(String name,double price,int stock,Category category,Image image){
        if(price <= 0){
            throw new IllegalArgumentException("price can't be negative!");
        }
        if(stock <= 0){
            throw new IllegalArgumentException("stock can't be negative!");
        }

        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.image = image;
        comments = new ArrayList<>();
        scores = new ArrayList<>();
        this.ID = idBuilder();
    }

    //making unique ID
    StringBuilder id = new StringBuilder();
    public String idBuilder(){
        id.append(name)
                .append(category.toString().substring(0,4))
                .append(Math.round(price));
        return id.toString();
    }

    @Override
    public String toString(){
        StringBuilder commentStrings = new StringBuilder();
        if(comments != null) {
            for (Comment comment : comments) {
                commentStrings.append(comment.toString());
            }
        }
        return String.format("\n%s\nPrice: %s\nScore: %s\nID: %s\n%s\n%s\n%s%s",
                getName(),getPrice(),getAveScore(),getID(),(getStock() == 0) ? "out of stock" : "only %d left in stock",getStock(),
                "Comments", "-----------------------------------------------------------------------------------------\n",
                commentStrings.toString());
    }

    @Override
    public int compareTo(Object object){
        Commodity obj2 = (Commodity) object;

        Admin admin = Admin.getAdmin();
        int thisIndex = -1;
        int obj2Index = -1;

        for (int i = 0; i < admin.getCommoditylistLen() ; i++) {
            if(admin.getCommodityList().get(i) == this){
                thisIndex = i;
            }
            if(admin.getCommodityList().get(i) == obj2){
                obj2Index = i;
            }
        }


        if(getSortBy() == SortBy.AVAILABILITY){
            if(this.getStock() > obj2.getStock()){
                return 1;
            }else if(this.getStock() < obj2.getStock()){
                return -1;
            }else {
                return 0;
            }
        }
        else if(getSortBy() == SortBy.SCORE){
            if(this.getAveScore() > obj2.getAveScore()){
                return 1;
            }else if(this.getAveScore() < obj2.getAveScore()){
                return -1;
            }else {
                return 0;
            }
        }
        else if(getSortBy() == SortBy.EXPENSIVENESS){
            if(this.getPrice() < obj2.getPrice()){
                return 1;
            }else if(this.getPrice() > obj2.getPrice()){
                return -1;
            }else {
                return 0;
            }
        }
        else if(getSortBy() == SortBy.CHEAPNESS){
            if(this.getPrice() > obj2.getPrice()){
                return 1;
            }else if(this.getPrice() < obj2.getPrice()){
                return -1;
            }else {
                return 0;
            }
        }else if(getSortBy() == SortBy.ALPHABET){
            return this.getName().compareTo(obj2.getName());
        }
        else if(getSortBy() == SortBy.RECENT){
            if(thisIndex > obj2Index){
                return -1;
            }else if(thisIndex < obj2Index){
                return 1;
            }else {
                return 0;
            }
        }
        else {
            if(thisIndex < obj2Index){
                return -1;
            }else if(thisIndex > obj2Index){
                return 1;
            }else {
                return 0;
            }
        }

    }

    //add methods
    public void addComment(Comment comment){
        comments.add(comment);
    }

    public void addScore(Score score){
        scores.add(score);
        calculateScore();
    }

    //getters
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public double getAveScore() {
        return aveScore;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public Category getCategory() {
        return category;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public Image getImage() {
        return image;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if(price <= 0){
            throw new IllegalArgumentException("price can't be negative!");
        }
        this.price = price;
    }

    public void setStock(int stock) {
        if(stock <= 0){
            throw new IllegalArgumentException("stock can't be negative!");
        }
        this.stock = stock;
    }

    public void setSortBy(SortBy sortBy) {
        this.sortBy = sortBy;
    }

    //calculate score
    public void calculateScore(){
        double total = 0;
        int counts = 0;

        for (Score score: scores){
            total += score.getScore();
            counts++;
        }

        aveScore = total / counts;
    }
}
