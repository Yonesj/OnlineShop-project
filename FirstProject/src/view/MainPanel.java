package view;

import control.CustomerControl;
import model.user.Admin;
import model.user.Customer;

import java.util.Scanner;

public class MainPanel {
    private static Scanner scanner = new Scanner(System.in);

    public MainPanel(){

    }

    public static void mainPage(){
        System.out.printf("\n[1] Sign up\n[2] Sign in\n[3] Products\n[4] Exit\n>>");

        int input = scanner.nextInt();

        switch (input){
            case 1:
                loggin();
                break;
            case 2:
                signIn();
                break;
            case 3:

                break;
            case 4:
                break;
            default:
                System.out.println("invalid command");
                mainPage();
                break;
        }

    }

    static void loggin(){
        scanner.nextLine();
        System.out.printf("\nEnter your username:\n>>");
        String inputUsername = scanner.nextLine();
        System.out.printf("Enter your password\n>>");
        String inputPass = scanner.nextLine();

        if(inputUsername.equals("admin") && inputPass.equals("admin")){
            System.out.println("\nwelcome to Admin panel!");
            AdminPanel.adminPage();
        }

        boolean found = false;
        for (Customer customer: CustomerControl.getCustomers()){
            if(customer.getUsername().equals(inputUsername)){
                found = true;
                if(customer.getPassword().equals(inputPass)){
                    CustomPanel customPanel = new CustomPanel(customer);
                    customPanel.customerPage();
                }else {
                    System.out.println("password is wrong!");
                    loggin();
                }
            }
        }

        if(!found){
            System.out.println("no account with this username has been fount! ");
            loggin();
        }
    }

    static void signIn(){
        scanner.nextLine();
        System.out.printf("\n###### SIGN IN ######\nusername:      ");
        String inputUsername = scanner.nextLine();
        System.out.printf("email:         ");
        String inputEmail = scanner.nextLine();
        System.out.printf("phone number:  ");
        String inpurPhone = scanner.nextLine();
        System.out.printf("password:      ");
        String inputPass = scanner.nextLine();

        System.out.println(CustomerControl.signIn(inputUsername,inputEmail,inpurPhone,inputPass));
        mainPage();
    }
}
