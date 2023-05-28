package model.commodity.subclasses;

import javafx.scene.image.Image;

public class USB extends DataStorage {
    //instance variables
    private String usbVersion;
    //constructor
    public USB(String name, double price, int stock, Image image, double weight, String size, String capacity, String usbVersion){
        super(name,price,stock,image,weight,size,capacity);
        this.usbVersion = usbVersion;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%s",super.toString() , "\nUSB Version:" , getUsbVersion());
    }

    //getter
    public String getUsbVersion() {
        return usbVersion;
    }
}
