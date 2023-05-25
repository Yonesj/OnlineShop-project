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

    @Override
    public String toString(){
        return String.format("%s:      %s\n%s\n%s",
                customer.getUsername(),text,(isBuyed) ? "user has buyed this product" : "user hasn't buyed this product",
                "-----------------------------------------------------------------------------------------\n");
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
