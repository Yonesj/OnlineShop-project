package model.user;

import model.commodity.Commodity;
import model.connectors.Invoice;

import java.util.ArrayList;

public class Customer extends User {
    //instance variables
    private double credit;
    private ArrayList<Commodity> cart;
    private ArrayList<Invoice> shoppinHistory;

    // Constructors
    public Customer(String username, String emailAddress, String phoneNumber, String password) {
        super(username, emailAddress, phoneNumber, password);
        cart = new ArrayList<>();
        shoppinHistory = new ArrayList<>();
    }

    //override method toString
    @Override
    public String toString(){
        return String.format("%-15s%s\n%-15s%s\n%-15s%s\n%-15s%f\n","Username:",getUsername()
                ,"Email Address:",getEmailAddress(),"Phone Number:",getPhoneNumber(),"Credit:",getCredit());
    }

    //add methods
    public void AddToCart(Commodity commodity){
        cart.add(commodity);
    }

    public void AddToShoppinHistory(Invoice invoice){
        shoppinHistory.add(invoice);
    }

    //getters
    public double getCredit() {
        return credit;
    }

    public ArrayList<Commodity> getCart() {
        return cart;
    }

    public ArrayList<Invoice> getShoppinHistory() {
        return shoppinHistory;
    }


    public void clearCart(){
        cart.clear();
    }
}
