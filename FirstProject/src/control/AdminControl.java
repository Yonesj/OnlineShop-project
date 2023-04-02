package control;

import model.connectors.RequestType;
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
                        int counts = 1;
                        StringBuilder viewRequests = new StringBuilder();
                        for (Request request: admin.getRequests()) {
                            if(request != null){
                                viewRequests.append("#" + counts);
                            }
                            viewRequests.append(request.toString());
                        }
                        return viewRequests.toString();

                    default:
                        return "invalid arguman,use help";
                }

            case "ManageRequest":
                int index = Integer.parseInt(subString[1]);
                boolean isAccepted;

                if(subString[2].equals("accept")){
                    isAccepted = true;
                }else if(subString[2].equals("reject")){
                    isAccepted = false;
                }else {
                    return "invalid arguman,use help";
                }

                if(index >= 1 && index <= admin.getRequestLen()){
                    Request request = admin.getRequest(index);

                    if(request.getRequestType() == RequestType.SIGNIN){
                        if(isAccepted) {
                            CustomerControl.addCustomer(request.getCustomer());
                        }
                        admin.removeReq(index);
                    }else if(request.getRequestType() == RequestType.COMMENT){

                    }else if(request.getRequestType() == RequestType.INCRESECREDIT){

                    }

                }else {
                    return  "out of bound error!";
                }

            case "Help":
                return String.format("%-20s%s\n","Help","Provides Help information for Admin commands.");
            default:
                return "invalid command,use help";
        }
    }

}
