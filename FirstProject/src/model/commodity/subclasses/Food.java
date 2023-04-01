package model.commodity.subclasses;

import model.commodity.Category;
import model.commodity.Commodity;

public class Food extends Commodity {
    //instance variables
    private final String manufactureDate;
    private final String expirationDate;

    //constructor
    public Food(String name, double price, int stock, String manufactureDate, String expirationDate){
        super(name,price,stock,Category.FOOD);
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
    }

    //getters
    public String getManufactureDate() {
        return manufactureDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
