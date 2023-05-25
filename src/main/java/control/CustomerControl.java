package control;

import exceptions.*;
import model.commodity.Commodity;
import model.connectors.Discount;
import model.connectors.Invoice;
import model.connectors.Request;
import model.user.Admin;
import model.user.Customer;
import view.CustomPanel;

import java.time.LocalDate;
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

    public static String signIn(String username,String email,String phone ,String password)
    throws InvalidEmailException, InvalidPhoneNumberException,InvalidPasswordException {
        for(Customer customer : customers){
            if(customer.getUsername().equals(username)){
                return "This username has been taken!";
            }
        }
        if(!emailPattern.matcher(email).find()){
            throw new InvalidEmailException();
        }
        if(!phoneNumberPattern.matcher(phone).find()){
            throw new InvalidPhoneNumberException("phone number must start with 09 and have 11 character length at all");
        }
        if(!passPattern.matcher(password).find()){
            throw new InvalidPasswordException("password must have at least 8 character, one letter and one number!");
        }


        Customer customer = new Customer(username,email,phone,password);
        Request request = new Request(customer);
        admin.addRequest(request);
        return "your request has been sent to admin";
    }

    public static String loggin(String inputUsername,String inputPassword)
    throws InvalidPasswordException{
        for (Customer customer: customers){
            if(customer.getUsername().equals(inputUsername)){
                if(customer.getPassword().equals(inputPassword)){
                    CustomPanel customPanel = new CustomPanel(customer);
                    customPanel.customerPage();
                }else {
                    throw new InvalidPasswordException("password is wrong!");
                }
            }
        }

        return  "no account with this username has been fount! ";
    }

    public static String editInfo(Customer customer,String newEmail,String newPhone,String newPass)
    throws InvalidPasswordException,InvalidEmailException,InvalidPhoneNumberException{
        boolean emailIsOkay = false;
        boolean passIsOkay = false;
        boolean phoneIsOkay = false;

        if(!newEmail.equals("")){
            if(!emailPattern.matcher(newEmail).find()){
                throw new InvalidEmailException();
            }
            emailIsOkay = true;
        }
        if(!newPhone.equals("")){
            if(!phoneNumberPattern.matcher(newPhone).find()){
                throw new InvalidPhoneNumberException("phone number must start with 09 and have 11 character length at all");
            }
            phoneIsOkay = true;
        }
        if(!newPass.equals("")){
            if(!passPattern.matcher(newPass).find()){
                throw new InvalidPasswordException("password must have at least 8 character, one letter and one number!");            }
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

    public static String finalizePurchase(Customer customer,String code)
            throws InsufficientBalanceException, InsufficientStockException, InvalidDiscountCodeException {
        Discount discount = null;
        boolean discountFound = false;
        for (Discount dis : customer.getDiscounts()){
            if(dis.getCode().equals(code)){
                discountFound = true;
                discount = dis;
                break;
            }
        }
        if(!discountFound){
            throw new InvalidDiscountCodeException("discount is invalid") ;
        }

        LocalDate now = LocalDate.now();
        if(now.isAfter(discount.getExpireDate())){
            throw new InvalidDiscountCodeException("this discount has been expired");
        }

        if(discount.getCapacity() == 0){
            throw new InvalidDiscountCodeException("this discount has been expired");
        }

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
                throw new InsufficientStockException("not enough stock!");
            }
        }

        amount -= amount * discount.getPercent() / 100;

        if(customer.getCredit() < amount){
            throw new InsufficientBalanceException("not enough credit!");
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