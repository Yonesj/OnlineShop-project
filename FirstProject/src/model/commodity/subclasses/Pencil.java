package model.commodity.subclasses;

public class Pencil extends Stationery {
    private PencilType pencilType;

    public Pencil(String name,double price,int stock,String madeIN,PencilType pencilType){
        super(name,price,stock,madeIN);
        this.pencilType = pencilType;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%s",super.toString() , "\nType:" , getPencilType());
    }

    public PencilType getPencilType() {
        return pencilType;
    }
}
