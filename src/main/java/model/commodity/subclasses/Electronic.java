package model.commodity.subclasses;

import javafx.scene.image.Image;
import model.commodity.Category;
import model.commodity.Commodity;
import model.connectors.DiscountInterface;

public abstract class Electronic extends Commodity implements DiscountInterface {
    //instance variables
    private double weight;
    private String size;
    private double percent;

    //constructors
    public Electronic(String name,double price,int stock,Image image,double weight,String size){
        super(name,price,stock, Category.ELECTRONIC,image);
        this.weight = weight;
        this.size = size;
    }

    @Override
    public String toString(){
        return String.format("%s\n%s%-20s%f%-20s%s",super.toString() ,"Technical Details:\n",
                "weight:" , getWeight() , "\nSize:" , getSize());
    }

    @Override
    public void addDiscount(double percent){
        this.percent = percent;
    }

    @Override
    public void removeDiscount(){
        this.percent = 0;
    }

    //getters
    public double getWeight() {
        return weight;
    }

    public String getSize() {
        return size;
    }

    public double getPercent() {
        return percent;
    }
}
