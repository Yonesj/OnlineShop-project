package model.commodity.subclasses;

import javafx.scene.image.Image;
import model.commodity.Category;
import model.commodity.Commodity;

public class Food extends Commodity {
    //instance variables
    private final String manufactureDate;
    private final String expirationDate;

    //constructor
    public Food(String name, double price, int stock, Image image, String manufactureDate, String expirationDate){
        super(name,price,stock,Category.FOOD,image);
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString(){
        return String.format("%s\n%s%-20s%s%-20s%s" ,super.toString() , "Technical Details:\n" ,
                "Manufacture Date:" ,  getManufactureDate() , "\nExpiration Date: " , getExpirationDate());
    }

    //getters
    public String getManufactureDate() {
        return manufactureDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
