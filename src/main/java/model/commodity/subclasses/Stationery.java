package model.commodity.subclasses;

import javafx.scene.image.Image;
import model.commodity.Category;
import model.commodity.Commodity;

public abstract class Stationery extends Commodity {
    //instance variables
    private String madeIN;
    //constructors
    public Stationery(String name, double price, int stock, Image image, String madeIN){
        super(name,price,stock, Category.STATIONERY,image);
        this.madeIN = madeIN;
    }

    @Override
    public String toString(){
        return String.format("%s\n%s%-20s%s",super.toString() , "Technical Details:\n" , "Maid in:" , getMadeIN());
    }

    //getter
    public String getMadeIN() {
        return madeIN;
    }
}
