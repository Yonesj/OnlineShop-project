package model.connectors;

import model.commodity.Commodity;
import model.user.Customer;

public class Score {
    Customer customer;
    double score;
    Commodity commodity;

    public Score(Customer customer, double score,Commodity commodity){
        this.customer = customer;
        this.score = score;
        this.commodity = commodity;
    }

    //getters
    public Customer getCustomer() {
        return customer;
    }

    public double getScore() {
        return score;
    }

    public Commodity getCommodity() {
        return commodity;
    }
}
