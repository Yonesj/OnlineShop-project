package model.connectors;
import model.user.Customer;
public class Comment {
    //instance variables
    private Customer customer;
    private String commodityID;
    private String text;
    private boolean isBuyed;
    private Status status;

    //constructor
    public Comment(Customer customer, String commodityID, String text, boolean isBuyed, Status status) {
        this.customer = customer;
        this.commodityID = commodityID;
        this.text = text;
        this.isBuyed = isBuyed;
        this.status = status;
    }

    //getters
    public Customer getCustomer() {
        return customer;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public String getText() {
        return text;
    }

    public boolean isBuyed() {
        return isBuyed;
    }

    public Status getStatus() {
        return status;
    }

    //setters
    public void setBuyed(boolean buyed) {
        isBuyed = buyed;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
