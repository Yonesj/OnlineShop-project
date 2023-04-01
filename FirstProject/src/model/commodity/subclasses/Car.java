package model.commodity.subclasses;

import model.commodity.Category;

public class Car extends Vehicle{
    private double engineVolume;
    private boolean isAuto;

    public Car(String name, double price, int stock, String company,double engineVolume,boolean isAuto){
        super(name,price,stock,company);
        this.engineVolume = engineVolume;
        this.isAuto = isAuto;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public boolean isAuto() {
        return isAuto;
    }
}
