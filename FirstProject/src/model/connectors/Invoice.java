package model.connectors;

import model.commodity.Commodity;

import java.util.ArrayList;
import java.util.Scanner;

public class Invoice {
    private ArrayList<Commodity> commodities;
    private final String date;
    private final double amount;
    private final String ID;

    public Invoice(double amount,String date){
        this.amount = amount;
        this.date = date;
        ID = date + (int)amount;
        commodities = new ArrayList<>();
    }

    //getters
    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getID() {
        return ID;
    }

    //add methods
    public void addToList(Commodity commodity){
        commodities.add(commodity);
    }
}
