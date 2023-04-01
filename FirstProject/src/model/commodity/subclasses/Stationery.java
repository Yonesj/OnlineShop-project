package model.commodity.subclasses;

import model.commodity.Category;
import model.commodity.Commodity;

public abstract class Stationery extends Commodity {
    //instance variables
    private String madeIN;
    //constructors
    public Stationery(String name,double price,int stock,String madeIN){
        super(name,price,stock, Category.STATIONERY);
        this.madeIN = madeIN;
    }

    //getter
    public String getMadeIN() {
        return madeIN;
    }
}
