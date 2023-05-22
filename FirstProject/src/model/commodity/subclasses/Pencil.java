package model.commodity.subclasses;

import model.connectors.DiscountInterface;

public class Pencil extends Stationery implements DiscountInterface {
    private PencilType pencilType;
    private double percent;

    public Pencil(String name,double price,int stock,String madeIN,PencilType pencilType){
        super(name,price,stock,madeIN);
        this.pencilType = pencilType;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%s",super.toString() , "\nType:" , getPencilType());
    }

    @Override
    public void addDiscount(double percent){
        this.percent = percent;
    }

    @Override
    public void removeDiscount(){
        this.percent = 0;
    }

    public PencilType getPencilType() {
        return pencilType;
    }
}
