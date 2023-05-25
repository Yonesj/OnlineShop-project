package model.user;

import model.connectors.Discount;

import java.util.ArrayList;

public abstract class User {
    //instance variables
    private final String username;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private ArrayList<Discount> discounts;


    // Constructors
    public User(String username,String emailAddress,String phoneNumber,String password){
        this.username = username;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        discounts = new ArrayList<>();
    }


    //override method toString
    @Override
    public abstract String toString();

    public void addDiscount(Discount discount){
        discounts.add(discount);
    }

    //getters
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername(){
        return username;
    }


    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public Discount getDiscount(int index){
        if(discounts.get(index -1) != null) {
            return discounts.get(index - 1);
        }else {
            return null;
        }
    }

    //setters

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
