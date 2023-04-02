package control;

import model.connectors.Request;
import model.user.Admin;
import model.user.Customer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerControl {
    private static Admin admin = Admin.getAdmin();
    private static ArrayList<Customer> customers = new ArrayList<>();;

    public CustomerControl(){

    }

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static String signIn(String username,String email,String phone ,String password){
        // pattens for validate input
        Pattern emailPattern = Pattern.compile("\\w+@(gmail|yahoo)\\.com");
        Pattern phoneNumberPattern = Pattern.compile("\\d{11}");
        Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher;

        for(Customer customer : customers){
            if(customer.getUsername().equals(username)){
                return "This username has been taken!";
            }
        }
        if(!emailPattern.matcher(email).find()){
            return "invalid email!";
        }
        if(!phoneNumberPattern.matcher(phone).find()){
            return "invalid phone number!";
        }
        if(!passPattern.matcher(password).find()){
            return "password must have at least 8 character, one letter and one number!";
        }


        Customer customer = new Customer(username,email,phone,password);
        Request request = new Request(customer);
        admin.addRequest(request);
        return "your request has been sent to admin";
    }
}
