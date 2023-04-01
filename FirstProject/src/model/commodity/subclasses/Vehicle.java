package model.commodity.subclasses;

import model.commodity.Category;
import model.commodity.Commodity;

public abstract class Vehicle extends Commodity {
    private String company;

    public Vehicle(String name, double price, int stock, String company){
        super(name,price,stock,Category.VEHICLE);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
}
