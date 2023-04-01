package model.commodity.subclasses;

public class SSD extends DataStorage {
    //instance variables
    private double readingSpeed;
    private double writingSpeed;
    //constructor
    public SSD(String name,double price,int stock,double weight,String size,String capacity,double readingSpeed,double writingSpeed){
        super(name,price,stock,weight,size,capacity);
        this.readingSpeed = readingSpeed;
        this.writingSpeed = writingSpeed;
    }

    //getter
    public double getReadingSpeed() {
        return readingSpeed;
    }

    public double getWritingSpeed() {
        return writingSpeed;
    }
}
