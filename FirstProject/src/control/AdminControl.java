package control;

import model.user.*;
import model.connectors.Request;

public class AdminControl {
    private static Admin admin = Admin.getAdmin();
    public static String processCommand(String command){
        String[] subString = command.split(" ");

        switch (subString[0]){
            case "Add":
            case "Edit":
            case "Remove":
            case "View":
                switch (subString[1]){
                    case "users":
                        StringBuilder viewUsers = new StringBuilder();
                        for (Customer customer: CustomerControl.getCustomers()) {
                            viewUsers.append(customer.toString());
                        }
                        return viewUsers.toString();

                    case "requests":
                        StringBuilder viewRequests = new StringBuilder();
                        for (Request request: admin.getRequests()) {
                            viewRequests.append(request.toString());
                        }
                        return viewRequests.toString();

                    default:
                        return "invalid arguman,use help";
                }
            case "Accept":
            case "Reject":
            case "Help":
                return String.format("%-20s%s\n","Help","Provides Help information for Admin commands.");
            default:
                return "invalid command,use help";
        }
    }

}
