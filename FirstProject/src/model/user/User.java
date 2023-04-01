package model.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {
    //instance variables
    private final String username;
    private String emailAddress;
    private String phoneNumber;
    private String password;

    // pattens for validate input
    private static Pattern emailPattern = Pattern.compile("\\w+@(gmail|yahoo)\\.com");
    private static Pattern phoneNumberPattern = Pattern.compile("\\d{11}");
    private static Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    private static Matcher matcher;

    // Constructors
    public User(String username,String emailAddress,String phoneNumber,String password){
        if(false){
            throw new IllegalArgumentException("This username has been taken!");
        }
        if(!emailPattern.matcher(emailAddress).find()){
            throw new IllegalArgumentException("invalid email!");
        }
        if(!phoneNumberPattern.matcher(phoneNumber).find()){
            throw new IllegalArgumentException("invalid phone number!");
        }
        if(!passPattern.matcher(password).find()){
            throw new IllegalArgumentException("password must have at least 8 character, one letter and one number!");
        }

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
        if(!emailPattern.matcher(emailAddress).find()){
            throw new IllegalArgumentException("invalid email!");
        }
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(!phoneNumberPattern.matcher(phoneNumber).find()){
            throw new IllegalArgumentException("invalid phone number!");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        if(!passPattern.matcher(password).find()){
            throw new IllegalArgumentException("password must have at least 8 character, one letter and one number!");
        }
        this.password = password;
    }
}
