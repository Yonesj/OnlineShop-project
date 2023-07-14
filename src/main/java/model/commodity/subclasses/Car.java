package model.commodity.subclasses;

import javafx.scene.image.Image;

public class Car extends Vehicle{
    private double engineVolume;
    private boolean isAuto;

    public Car(String name, double price, int stock, Image image, String company, double engineVolume, boolean isAuto){
        super(name,price,stock,image,company);
        this.engineVolume = engineVolume;
        this.isAuto = isAuto;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%f%-20s%b",super.toString() , "\nEngine Volume:" , getEngineVolume() , "\nAuto:" , isAuto());
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public boolean isAuto() {
        return isAuto;
    }
}
