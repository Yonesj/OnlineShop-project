package model.connectors;

import model.commodity.Commodity;
import model.user.Customer;

public class Request {
    //instance variables
    private RequestType requestType;
    private Customer customer;
    private Comment comment;
    private Commodity commodity;
    private double amount;
    private boolean status;

    //3 overloaded constructors
    public Request(Customer customer){
        this.customer = customer;
        requestType = RequestType.SIGNIN;
    }
    public Request(Customer customer,Comment comment,Commodity commodity){
        this.comment = comment;
        this.customer = customer;
        this.commodity = commodity;
        requestType = RequestType.COMMENT;
    }
    public Request(Customer customer,double amount){
        this.customer = customer;
        this.amount = amount;
        requestType = RequestType.INCRESECREDIT;
    }

    //override method toString
    @Override
    public String toString(){
        if(requestType == RequestType.SIGNIN){
            return String.format("%s\n%s\n%s","Request type: Sign in","user information:",customer.toString());
        }else if(requestType == RequestType.COMMENT){
            return String.format("%s\n%s", "Request type: Comment",comment.toString() );
        }else {
            return String.format("%s\n%-15s%s\n%-15s%f","Request type: Increase Credit","user:",customer.getUsername(),"Amount:",amount);
        }
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

    public Commodity getCommodity() {
        return commodity;
    }
}
