package model.connectors;

import model.commodity.Commodity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice {
    private ArrayList<Commodity> commodities;
    private final LocalDate date;
    private final double amount;
    private final String ID;

    public Invoice(double amount,LocalDate date){
        this.amount = amount;
        this.date = date;
        ID = date.toString() + (int)amount;
        commodities = new ArrayList<>();
    }

    //getters
    public ArrayList<Commodity> getCommodities() {
        return commodities;
    }

    public LocalDate getDate() {
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
