package model.commodity;

import model.connectors.Comment;
import model.connectors.Score;

import java.util.ArrayList;

public abstract class Commodity {
    // instance variables
    private String ID;
    private String name;
    private double price;
    private int stock;
    private double aveScore;
    private ArrayList<Comment> comments;
    private ArrayList<Score> scores;
    private Category category;

    //constructors
    public Commodity(String name,double price,int stock,Category category){
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
    }

    //making unique ID
    StringBuilder idBuilder = new StringBuilder(name.substring(0,3))
            .append(category.toString().substring(0,4))
            .append(price);

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
}
