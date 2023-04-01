package model.commodity.subclasses;

public abstract class DataStorage extends Electronic {
    //instance variables
    private String capacity;
    //constructor
    public DataStorage(String name,double price,int stock,double weight,String size,String capacity){
        super(name,price,stock,weight,size);
        this.capacity = capacity;
    }

    //getter
    public String getCapacity(){
        return capacity;
    }
}
