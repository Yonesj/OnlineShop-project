package model.commodity.subclasses;

public class Pen extends Stationery{
    private String color;

    public Pen(String name,double price,int stock,String madeIN,String color){
        super(name,price,stock,madeIN);
        this.color = color;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%s",super.toString() , "\nColor:" , getColor());
    }

    public String getColor() {
        return color;
    }
}
