package model.commodity.subclasses;

import model.commodity.Category;
import model.commodity.Commodity;

public abstract class Vehicle extends Commodity {
    private String company;

    public Vehicle(String name, double price, int stock, String company){
        super(name,price,stock,Category.VEHICLE);
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
