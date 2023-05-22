package view;
import control.CommodityControl;
import control.CustomerControl;
import model.connectors.Invoice;
import model.user.Customer;
import model.commodity.Commodity;

import java.util.Scanner;


public class CustomPanel {
    private Customer customer;
    private Scanner scanner;

    public CustomPanel(Customer customer){
        this.customer = customer;
        scanner = new Scanner(System.in);
    }

    public void customerPage(){
        System.out.printf("welcome to your panel %s\n[1] View personal info\n[2] Cart\n[3] Increase Credit\n[4] Product Panel\n[5] Shopping History\n[6] Logout\n>>", customer.getUsername());

        int input = scanner.nextInt();

        switch (input){
            case 1:
                viewInfo();
            case 2:
                viewCart();
            case 3:
                purchasePage();
            case 4:
                CommodityPanel commodityPanel = new CommodityPanel(customer);
                commodityPanel.showPage(1);
                break;
            case 5:
                viewShoppinHistory();
                customerPage();
                break;
            case 6:
                MainPanel.mainPage();
                break;
        }
    }

    private void viewInfo(){
        System.out.println(customer.toString());
        System.out.println("[1] back      [2] Edit");
        int input = scanner.nextInt();

        if(input == 2){
            editInfo();
        }else {
            customerPage();
        }
    }

    private void editInfo(){
        scanner.nextLine();
        System.out.printf("Press enter if you dont want to change a field\nemail address: %s\n>>",customer.getEmailAddress());
        String newEmail = scanner.nextLine();
        System.out.printf("phone number: %s\n>>",customer.getPhoneNumber());
        String newPhone = scanner.nextLine();
        System.out.printf("password: %s\n>>",customer.getPassword());
        String newPass = scanner.nextLine();

        System.out.println(CustomerControl.editInfo(customer,newEmail,newPhone,newPass));
        customerPage();
    }

    private void viewCart(){
        for (Commodity commodity : customer.getCart()) {
            System.out.println(commodity.toString());
        }
        System.out.printf("[1] Back      [2] Complete purchase      [3] Clear Cart\n>>");
        int input = scanner.nextInt();

        switch (input){
            case 1:
                customerPage();
                break;
            case 2:
                System.out.printf("Enter your Discount\n>>");
                String dizzcode = scanner.nextLine();
                System.out.println(CustomerControl.finalizePurchase(customer,dizzcode));
                customerPage();
                break;
            case 3:
                customer.clearCart();
                customerPage();
                break;
        }
    }

    private void purchasePage(){
        scanner.nextLine();
        System.out.printf("###### checkout portal ######\ncredit card:   ");
        String creditCard = scanner.nextLine();
        System.out.printf("password:      ");
        int password = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("CVV2:          ");
        String cvv2 = scanner.nextLine();
        System.out.printf("Amount:        ");
        double amount = scanner.nextDouble();

        System.out.println(CustomerControl.increaseCreditReq(customer,creditCard,password,cvv2,amount));
        customerPage();
    }

    private void viewShoppinHistory(){
        for (Invoice invoice : customer.getShoppinHistory()){
            for (Commodity commodity: invoice.getCommodities()){
                System.out.println(commodity.toString());
            }
        }
    }
}
