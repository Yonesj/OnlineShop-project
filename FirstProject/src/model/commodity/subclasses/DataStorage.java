package model.commodity.subclasses;

public abstract class DataStorage extends Electronic {
    //instance variables
    private String capacity;
    //constructor
    public DataStorage(String name,double price,int stock,double weight,String size,String capacity){
        super(name,price,stock,weight,size);
        this.capacity = capacity;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%s",super.toString() , "\nCapacity:" , getCapacity());
    }

    //getter
    public String getCapacity(){
        return capacity;
    }
}
