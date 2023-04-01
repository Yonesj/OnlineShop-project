package model.user;

import model.commodity.Commodity;
import model.connectors.Request;

import java.util.ArrayList;

public class Admin extends User {
    private static Admin admin;
    private ArrayList<Commodity> commodityList;
    private ArrayList<Request> requests;

    private Admin(String username,String emailAddress,String phoneNumber,String password){
        super(username,emailAddress,phoneNumber,password);
        commodityList = new ArrayList<>();
        requests = new ArrayList<>();
    }

    //override method toString
    @Override
    public String toString(){
        return String.format("%-15s%s\n%-15s%s\n%-15s%s\n","Username:",getUsername()
                ,"Email Address:",getEmailAddress(),"Phone Number:",getPhoneNumber());
    }

    //getters
    public static Admin getAdmin() {
        if(admin == null){
            admin = new Admin("admin","admin@gmail.com","09123456789","admin1382");
        }
        return admin;
    }

    public ArrayList<Commodity> getCommodityList() {
        return commodityList;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }
}
