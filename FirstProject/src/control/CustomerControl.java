package control;

import model.user.Customer;

import java.util.ArrayList;

public class CustomerControl {
    private static ArrayList<Customer> customers = new ArrayList<>();;

    public CustomerControl(){

    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }
}
