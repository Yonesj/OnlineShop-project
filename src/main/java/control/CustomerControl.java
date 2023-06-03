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

    public CustomerControl(){

    }

    public static void addCustomer(Customer customer){
        customers.add(customer);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static void signIn(String username,String email,String phone ,String password)
    throws InvalidEmailException, InvalidPhoneNumberException,InvalidPasswordException,NotAvailableUsernameException {

        if(!NotAvailableUsernameException.isUsernameAvailable(username)){
            throw new NotAvailableUsernameException("this username is not available");
        }
        if(!InvalidEmailException.isEmailValid(email)){
            throw new InvalidEmailException("email is not valid");
        }
        if(!InvalidPhoneNumberException.isPhoneValid(phone)){
            throw new InvalidPhoneNumberException("phone number must start with 09 and have 11 character length at all");
        }
        if(!InvalidPasswordException.isPassValid(password)){
            throw new InvalidPasswordException("password must have at least 8 character, one letter and one number!");
        }


        Customer customer = new Customer(username,email,phone,password);
//        Request request = new Request(customer);
//        admin.addRequest(request);
        customers.add(customer);
//        return "your request has been sent to admin";
    }

    public static Customer loggin(String inputUsername,String inputPassword)
            throws InvalidPasswordException, NotFound404Exception {
        for (int i=0; i < CustomerControl.getCustomers().size(); i++){
            Customer customer = CustomerControl.customers.get(i);
            if(customer.getUsername().equals(inputUsername)){
                if(customer.getPassword().equals(inputPassword)){
                    return customer;
                }else {
                    throw new InvalidPasswordException("password is wrong!");
                }
            }
        }

        throw new NotFound404Exception("no account with this username has been fount! ");
    }

    public static String editInfo(Customer customer,String newEmail,String newPhone,String newPass)
    throws InvalidPasswordException,InvalidEmailException,InvalidPhoneNumberException{
        boolean emailIsOkay = false;
        boolean passIsOkay = false;
        boolean phoneIsOkay = false;

        if(!newEmail.equals("")){
            if(!InvalidEmailException.isEmailValid(newEmail)){
                throw new InvalidEmailException();
            }
            emailIsOkay = true;
        }
        if(!newPhone.equals("")){
            if(!InvalidPhoneNumberException.isPhoneValid(newPhone)){
                throw new InvalidPhoneNumberException("phone number must start with 09 and have 11 character length at all");
            }
            phoneIsOkay = true;
        }
        if(!newPass.equals("")){
            if(!InvalidPasswordException.isPassValid(newPass)){
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


    public static void increaseCreditReq(Customer customer,String creditCard,String cvv2,double amount)
            throws InvalidCardException, InvalidCvv2Exception, InvalidPurchaseException {
        if(!InvalidCardException.isCardValid(creditCard)){
            throw new InvalidCardException( "invalid credit card");
        }
        if(!InvalidCvv2Exception.isCvv2Valid(cvv2)){
            throw new InvalidCvv2Exception("invalid cvv2!");
        }
        if(amount <= 0){
            throw new InvalidPurchaseException("amount could not be negetive!");
        }

        Request request = new Request(customer,amount);
        admin.addRequest(request);
    }

    public static String finalizePurchase(Customer customer,String code)
            throws InsufficientBalanceException, InsufficientStockException, InvalidDiscountCodeException {
        Discount discount = null;
        if(code != null) {
            boolean discountFound = false;
            for (Discount dis : customer.getDiscounts()) {
                if (dis.getCode().equals(code)) {
                    discountFound = true;
                    discount = dis;
                    break;
                }
            }
            if (!discountFound) {
                throw new InvalidDiscountCodeException("discount is invalid");
            }

            LocalDate now = LocalDate.now();
            if (now.isAfter(discount.getExpireDate())) {
                throw new InvalidDiscountCodeException("this discount has been expired");
            }

            if (discount.getCapacity() == 0) {
                throw new InvalidDiscountCodeException("this discount has been expired");
            }
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

        if(discount != null) {
            amount -= amount * (discount.getPercent() / 100);
        }

        if(customer.getCredit() < amount){
            throw new InsufficientBalanceException("not enough credit!");
        }

        customer.setCredit(customer.getCredit() - amount);
        LocalDate currentDate = LocalDate.now();
        Invoice invoice = new Invoice(amount,currentDate);

        for (Commodity commodity: customer.getCart()){
            commodity.setStock(commodity.getStock() - 1);
            invoice.addToList(commodity);
        }
        customer.AddToShoppinHistory(invoice);
        customer.clearCart();
        return "operation was successful!";
    }
}
