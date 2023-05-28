package model.commodity.subclasses;

import javafx.scene.image.Image;
import model.connectors.DiscountInterface;

public class Pen extends Stationery implements DiscountInterface {
    private String color;
    private double percent;

    public Pen(String name, double price, int stock, Image image, String madeIN, String color){
        super(name,price,stock,image,madeIN);
        this.color = color;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%s",super.toString() , "\nColor:" , getColor());
    }

    @Override
    public void addDiscount(double percent){
        this.percent = percent;
    }

    @Override
    public void removeDiscount(){
        this.percent = 0;
    }

    public String getColor() {
        return color;
    }
}
