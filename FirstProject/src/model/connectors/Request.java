package model.connectors;

import model.user.Customer;

public class Request {
    //instance variables
    private RequestType requestType;
    private Customer customer;
    private Comment comment;
    private double amount;
    private boolean status;

    //3 overloaded constructors
    public Request(Customer customer){
        this.customer = customer;
        requestType = RequestType.SIGNIN;
    }
    public Request(Customer customer,Comment comment){
        this.comment = comment;
        this.customer = customer;
        requestType = RequestType.COMMENT;
    }
    public Request(Customer customer,double amount){
        this.customer = customer;
        this.amount = amount;
        requestType = RequestType.INCRESECREDIT;
    }


    //getters
    public RequestType getRequestType() {
        return requestType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Comment getComment() {
        return comment;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isStatus() {
        return status;
    }

    //setters
    public void setStatus(boolean confirmation){
        status = confirmation;
    }
}
