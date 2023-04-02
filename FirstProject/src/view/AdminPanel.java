package view;

import control.AdminControl;

import java.util.Scanner;

public class AdminPanel {
    static Scanner scanner = new Scanner(System.in);
    public static void adminPage(){
        System.out.printf("Enter your command(enter help to see all commands)\n>>");
        String command = scanner.nextLine();

        if(command.equals("Logout")){
            MainPanel.mainPage();
        }

        System.out.println(AdminControl.processCommand(command));
        AdminPanel.adminPage();
    }
}
