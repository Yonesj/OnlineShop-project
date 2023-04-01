package model.commodity.subclasses;

public class Bicycle extends Vehicle {
    private BicycleType bicycleType;

    public Bicycle(String name, double price, int stock, String company,BicycleType bicycleType){
        super(name,price,stock,company);
        this.bicycleType = bicycleType;
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }
}
