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

    //add methods
    public void addRequest(Request request){
        requests.add(request);
    }

    public void addCommodity(Commodity commodity){
        commodityList.add(commodity);
    }

    //remove methods
    public void removeReq(int index){
        requests.remove(index - 1);
    }

    public void removeCom(Commodity commodity){
        commodityList.remove(commodity);
    }

    //getters
    public static Admin getAdmin() {
        if(admin == null){
            admin = new Admin("admin","admin@gmail.com","09123456789","admin");
        }
        return admin;
    }

    public ArrayList<Commodity> getCommodityList() {
        return commodityList;
    }

    public int getCommoditylistLen(){
        return commodityList.size();
    }

    public Commodity getCommodity(int index){
        if(commodityList.size() > index) {
            return commodityList.get(index);
        }else {
            return null;
        }
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public int getRequestLen(){
        return requests.size();
    }

    public Request getRequest(int index){
        return requests.get(index - 1);
    }
}
