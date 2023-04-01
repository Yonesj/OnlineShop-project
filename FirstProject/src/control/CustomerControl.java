package control;

import model.user.Customer;

import java.util.ArrayList;

public class CustomerControl {
    private static ArrayList<Customer> customers;

    public CustomerControl(){
        customers = new ArrayList<>();
    }

    public static ArrayList<Customer> getUsers() {
        return customers;
    }
}
