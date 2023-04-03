package view;
import control.CustomerControl;
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
        System.out.printf("welcome to your panel %s\n[1] Edit personal info\n[2] ", customer.getUsername());

        int input = scanner.nextInt();

        switch (input){
            case 1:
                editInfo();
            case 2:
        }
    }

    private void editInfo(){
        System.out.printf("Press enter if you dont want to change a field\nemail address: %s\n>>",customer.getEmailAddress());
        String newEmail = scanner.nextLine();
        System.out.printf("phone number: %s\n>>",customer.getPhoneNumber());
        String newPhone = scanner.nextLine();
        System.out.printf("password: %s\n>>",customer.getPassword());
        String newPass = scanner.nextLine();

        System.out.println(CustomerControl.editInfo(customer,newEmail,newPhone,newPass));
        customerPage();
    }
}
