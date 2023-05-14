package control;

import model.commodity.Commodity;
import model.connectors.Invoice;
import model.connectors.Request;
import model.user.Admin;
import model.user.Customer;
import view.CustomPanel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerControl {
    private static Admin admin = Admin.getAdmin();
    private static ArrayList<Customer> customers = new ArrayList<>();;

    // pattens for validate input
    private static Pattern emailPattern = Pattern.compile("\\w+@(gmail|yahoo)\\.com");
    private static Pattern phoneNumberPattern = Pattern.compile("09\\d{9}");
    private static Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    private static Matcher matcher;

    public CustomerControl(){

    }

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static String signIn(String username,String email,String phone ,String password){
        for(Customer customer : customers){
            if(customer.getUsername().equals(username)){
                return "This username has been taken!";
            }
        }
        if(!emailPattern.matcher(email).find()){
            return "invalid email!";
        }
        if(!phoneNumberPattern.matcher(phone).find()){
            return "invalid phone number!";
        }
        if(!passPattern.matcher(password).find()){
            return "password must have at least 8 character, one letter and one number!";
        }


        Customer customer = new Customer(username,email,phone,password);
        Request request = new Request(customer);
        admin.addRequest(request);
        return "your request has been sent to admin";
    }

    public static String loggin(String inputUsername,String inputPassword){
        for (Customer customer: customers){
            if(customer.getUsername().equals(inputUsername)){
                if(customer.getPassword().equals(inputPassword)){
                    CustomPanel customPanel = new CustomPanel(customer);
                    customPanel.customerPage();
                }else {
                    return  "password is wrong(press Enter)!";
                }
            }
        }

        return  "no account with this username has been found!(press Enter) ";
    }

    public static String editInfo(Customer customer,String newEmail,String newPhone,String newPass){
        boolean emailIsOkay = false;
        boolean passIsOkay = false;
        boolean phoneIsOkay = false;

        if(!newEmail.equals("")){
            if(!emailPattern.matcher(newEmail).find()){
                return "invalid email!";
            }
            emailIsOkay = true;
        }
        if(!newPhone.equals("")){
            if(!phoneNumberPattern.matcher(newPhone).find()){
                return "invalid phone number!";
            }
            phoneIsOkay = true;
        }
        if(!newPass.equals("")){
            if(!passPattern.matcher(newPass).find()){
                return "password must have at least 8 character, one letter and one number!";
            }
            passIsOkay = true;
        }

        if(emailIsOkay){
            customer.setEmailAddress(newEmail);
        }
        if(phoneIsOkay){
            customer.setPhoneNumber(newPhone);
        }
        if(passIsOkay){
            customer.setPassword(newPass);
        }

        return "information has been edited successfully!";
    }


    public static String increaseCreditReq(Customer customer,String creditCard,int password,String cvv2,double amount){
        Pattern cardPattern = Pattern.compile("\\d{4}( |-)\\d{4}( |-)\\d{4}( |-)\\d{4}");
        Pattern cvv2Pattern = Pattern.compile("\\d{3,4}");

        if(!cardPattern.matcher(creditCard).find()){
            return "invalid credit card";
        }
        if(!cvv2Pattern.matcher(cvv2).find()){
            return "invalid cvv2!";
        }
        if(amount <= 0){
            return "amount could not be negetive!";
        }

        Request request = new Request(customer,amount);
        admin.addRequest(request);
        return "your request has been sent to admin";
    }

    public static String finalizePurchase(Customer customer){
        double amount = 0;

        for (int i=0; i < customer.getCart().size(); i++){
            amount += customer.getCart().get(i).getPrice();
            int count = 1;
            for (int j=i; j < customer.getCart().size(); j++){
                if(customer.getCart().get(i) == customer.getCart().get(j)){
                    count++;
                }
            }
            if(count > customer.getCart().get(i).getStock()){
                return "not enough stock!";
            }
        }

        if(customer.getCredit() < amount){
            return "not enough credit!";
        }

        customer.setCredit(customer.getCredit() - amount);
        Invoice invoice = new Invoice(amount,"1402/01/14");

        for (Commodity commodity: customer.getCart()){
            commodity.setStock(commodity.getStock() - 1);
            invoice.addToList(commodity);
        }
        customer.AddToShoppinHistory(invoice);
        customer.clearCart();
        return "operation was successful!";
    }
}
