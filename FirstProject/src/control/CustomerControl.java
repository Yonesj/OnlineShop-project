package control;

import model.user.Customer;

import java.util.ArrayList;

public class CustomerControl {
    private static ArrayList<Customer> customers = new ArrayList<>();;

    public CustomerControl(){

    }

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }
}
