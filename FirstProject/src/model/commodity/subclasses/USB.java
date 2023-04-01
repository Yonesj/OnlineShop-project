package model.commodity.subclasses;

public class USB extends DataStorage {
    //instance variables
    private String usbVersion;
    //constructor
    public USB(String name,double price,int stock,double weight,String size,String capacity,String usbVersion){
        super(name,price,stock,weight,size,capacity);
        this.usbVersion = usbVersion;
    }

    //getter
    public String getUsbVersion() {
        return usbVersion;
    }
}
