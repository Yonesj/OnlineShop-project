package model.user;

import model.commodity.Commodity;
import model.connectors.Invoice;

import java.util.ArrayList;

public class Customer extends User {
    private double credit;
    private ArrayList<Commodity> cart;
    private ArrayList<Invoice> shoppinHistory;

    public Customer(String username, String emailAddress, String phoneNumber, String password) {
        super(username, emailAddress, phoneNumber, password);
        cart = new ArrayList<>();
        shoppinHistory = new ArrayList<>();
    }
}
