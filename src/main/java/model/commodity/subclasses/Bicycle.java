package model.commodity.subclasses;

import javafx.scene.image.Image;

public class Bicycle extends Vehicle {
    private BicycleType bicycleType;

    public Bicycle(String name, double price, int stock, Image image, String company, BicycleType bicycleType){
        super(name,price,stock,image,company);
        this.bicycleType = bicycleType;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%s",super.toString() , "\nType:" , getBicycleType().toString());
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }
}
