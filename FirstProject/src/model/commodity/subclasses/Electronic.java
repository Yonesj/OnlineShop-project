package model.commodity.subclasses;

import model.commodity.Category;
import model.commodity.Commodity;

public abstract class Electronic extends Commodity {
    //instance variables
    private double weight;
    private String size;

    //constructors
    public Electronic(String name,double price,int stock,double weight,String size){
        super(name,price,stock, Category.ELECTRONIC);
        this.weight = weight;
        this.size = size;
    }

    //getters
    public double getWeight() {
        return weight;
    }

    public String getSize() {
        return size;
    }
}
