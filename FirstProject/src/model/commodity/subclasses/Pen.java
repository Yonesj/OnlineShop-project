package model.commodity.subclasses;

public class Pen extends Stationery{
    private String color;

    public Pen(String name,double price,int stock,String madeIN,String color){
        super(name,price,stock,madeIN);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
