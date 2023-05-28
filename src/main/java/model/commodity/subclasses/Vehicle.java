package model.commodity.subclasses;

import javafx.scene.image.Image;
import model.commodity.Category;
import model.commodity.Commodity;

public abstract class Vehicle extends Commodity {
    private String company;

    public Vehicle(String name, double price, int stock, Image image, String company){
        super(name,price,stock,Category.VEHICLE,image);
        this.company = company;
    }

    @Override
    public String toString(){
        return String.format("%s\n%s%-20s%s",super.toString() , "Technical Details:\n" , "Company:" , getCompany());
    }

    public String getCompany() {
        return company;
    }
}
