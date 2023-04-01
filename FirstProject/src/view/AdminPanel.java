package view;

import control.CustomerControl;
import model.user.Customer;

import java.util.Scanner;

public class AdminPanel {
    private static Scanner scanner = new Scanner(System.in);;

    public AdminPanel(){

    }

    public static void adminPage(){
        System.out.println("Welcome to admin panel!\n[1] View users\n[2] Manage requests\n[3] Add product\n[4] log out");
        int commant = scanner.nextInt();

        switch (commant){
            case 1:
                viewUsers();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                MainPanel.mainPage();
                break;
            default:
                System.out.println("Invalid command!");
                AdminPanel.adminPage();
                break;
        }

    }

    private static void viewUsers(){
        for (Customer customer : CustomerControl.getCustomers()){
            if(customer != null) {
                System.out.println(customer.toString());
            }
        }
    }
}
