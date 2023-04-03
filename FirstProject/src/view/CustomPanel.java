package view;
import model.user.Customer;

import java.util.Scanner;

public class CustomPanel {
    private Customer customer;
    private Scanner scanner;

    public CustomPanel(Customer customer){
        this.customer = customer;
        scanner = new Scanner(System.in);
    }

    public void customerPage(){
        System.out.printf("welcome to your panel %s\n", customer.getUsername());
    }
}
