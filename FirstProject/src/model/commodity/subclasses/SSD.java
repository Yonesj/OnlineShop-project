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

    @Override
    public String toString(){
        return String.format("%s%-20s%f%-20s%f",super.toString() + "\nReading Speed:" + getReadingSpeed() + "\nWriting Speed:" + getWritingSpeed());
    }

    //getter
    public double getReadingSpeed() {
        return readingSpeed;
    }

    public double getWritingSpeed() {
        return writingSpeed;
    }
}
