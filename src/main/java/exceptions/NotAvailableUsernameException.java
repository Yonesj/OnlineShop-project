package exceptions;

import control.CustomerControl;
import model.user.Customer;

public class NotAvailableUsernameException extends Exception{
    public NotAvailableUsernameException(){
        super("NotAvailableUsernameException");
    }
    public NotAvailableUsernameException(String errorMassage){
        super(errorMassage);
    }

    public static boolean isUsernameAvailable(String username){
        for(Customer customer : CustomerControl.getCustomers()){
            if(customer.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }
}
