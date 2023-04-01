package model.commodity.subclasses;

import model.commodity.Category;

public class PersonalComputer extends Electronic{
    //instance variables
    private String cpuModel;
    private String ramMemmory;

    //constructor
    public PersonalComputer(String name,double price,int stock,double weight,String size,String cpuModel,String ramMemmory){
        super(name,price,stock,weight,size);
        this.cpuModel = cpuModel;
        this.ramMemmory = ramMemmory;
    }

    //getters

    public String getCpuModel() {
        return cpuModel;
    }

    public String getRamMemmory() {
        return ramMemmory;
    }
}
