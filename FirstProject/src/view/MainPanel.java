package view;

import model.user.Admin;

import java.util.Scanner;

public class MainPanel {
    private static Scanner scanner = new Scanner(System.in);

    public MainPanel(){

    }

    public static void mainPage(){
        System.out.println("[1] Sign up\n[2] Sign in\n[3] Products\n[4] Exit");

        int input = scanner.nextInt();

        switch (input){
            case 1:
                loggin();
                break;
            case 2:

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
        System.out.println("Enter your username:");
        String inputUsername = scanner.nextLine();
        System.out.println("Enter your password");
        String inputPass = scanner.nextLine();

        if(inputUsername.equals("admin") && inputPass.equals("admin1382")){

        }
    }
}
