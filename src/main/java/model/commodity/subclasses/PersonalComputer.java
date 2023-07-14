package model.commodity.subclasses;

import javafx.scene.image.Image;

public class PersonalComputer extends Electronic{
    //instance variables
    private String cpuModel;
    private int ramMemmory;

    //constructor
    public PersonalComputer(String name, double price, int stock, Image image, double weight, String size, String cpuModel, int ramMemmory){
        super(name,price,stock,image,weight,size);
        this.cpuModel = cpuModel;
        this.ramMemmory = ramMemmory;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%s%-20s%d",super.toString() , "\nCPU:" , getCpuModel() , "\nRam:" , getRamMemmory());
    }

    //getters
    public String getCpuModel() {
        return cpuModel;
    }

    public int getRamMemmory() {
        return ramMemmory;
    }
}
