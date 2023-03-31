package view;

import java.util.Scanner;

public class MainPanel {
    private static Scanner scanner;

    public MainPanel(){
        scanner = new Scanner(System.in);
    }

    public void mainPage(){
        System.out.println("[1] Sign up\n[2] Sign in\n[3] Products\n[4] Exit");

        int input = scanner.nextInt();

        switch (input){
            case 1:
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
}
