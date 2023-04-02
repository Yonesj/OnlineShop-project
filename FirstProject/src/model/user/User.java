package model.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {
    //instance variables
    private final String username;
    private String emailAddress;
    private String phoneNumber;
    private String password;


    // Constructors
    public User(String username,String emailAddress,String phoneNumber,String password){
        this.username = username;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    //override method toString
    @Override
    public abstract String toString();

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
