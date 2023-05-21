package control;

import model.commodity.Commodity;
import model.commodity.subclasses.*;
import model.user.Admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CommodityControl {
    private Admin admin;
    private ArrayList<Commodity> Seed;
    private boolean allProductsFilet = true;
    private boolean electronicFilter = false;
    private boolean pcFilter = false;
    private boolean dataStorageFilter = false;
    private boolean stationeryFilter = false;
    private boolean penFilter = false;
    private boolean pencilFilter = false;
    private boolean noteBookFilter = false;
    private boolean foodFilter = false;
    private boolean vehicleFilter = false;
    private boolean carFilter = false;
    private boolean bicycleFilter = false;
    private boolean avaliableFilter= false;
    private double priceUPlimit = -1;
    private double priceDOWNlimit = -1;
    private float scoreLimit = 0;

    public CommodityControl(){
        admin = Admin.getAdmin();
        Seed = new ArrayList<>(admin.getCommodityList());
    }

    public void fillSeed(){
        Seed.clear();
        if(foodFilter || electronicFilter || vehicleFilter || stationeryFilter || pcFilter|| dataStorageFilter || carFilter || bicycleFilter|| noteBookFilter || pencilFilter || penFilter){
            for (Commodity commodity : admin.getCommodityList()){
                //general filters
                if(commodity.getAveScore() < scoreLimit){
                    continue;
                }
                if(avaliableFilter && (commodity.getStock() == 0)){
                    continue;
                }
                if(priceDOWNlimit != -1 && (commodity.getPrice() < priceDOWNlimit)){
                    continue;
                }
                if(priceUPlimit != -1 && (commodity.getPrice() > priceUPlimit)){
                    continue;
                }
                //filter by department
                if(foodFilter && commodity instanceof Food){
                    Seed.add(commodity);
                }else if(electronicFilter && commodity instanceof Electronic){
                    Seed.add(commodity);
                }else if(vehicleFilter && commodity instanceof Vehicle){
                    Seed.add(commodity);
                }else if(stationeryFilter && commodity instanceof Stationery){
                    Seed.add(commodity);
                }else if(pcFilter && commodity instanceof PersonalComputer){
                    Seed.add(commodity);
                }else if(dataStorageFilter && commodity instanceof DataStorage){
                    Seed.add(commodity);
                }else if(carFilter && commodity instanceof Car){
                    Seed.add(commodity);
                }else if(bicycleFilter && commodity instanceof Bicycle){
                    Seed.add(commodity);
                }else if(penFilter && commodity instanceof Pen){
                    Seed.add(commodity);
                }else if(pencilFilter && commodity instanceof Pencil){
                    Seed.add(commodity);
                }else if(noteBookFilter && commodity instanceof NoteBook){
                    Seed.add(commodity);
                }
            }
        }else {
            for (Commodity commodity : admin.getCommodityList()) {
                if(commodity.getAveScore() < scoreLimit){
                    continue;
                }
                if(avaliableFilter && (commodity.getStock() == 0)){
                    continue;
                }
                if(priceDOWNlimit != -1 && (commodity.getPrice() < priceDOWNlimit)){
                    continue;
                }
                if(priceUPlimit != -1 && (commodity.getPrice() > priceUPlimit)){
                    continue;
                }

                Seed.add(commodity);
            }
        }

        Collections.sort(getSeed());
    }

    //getters
    public boolean isAllProductsFilet() {
        return allProductsFilet;
    }

    public boolean isElectronicFilter() {
        return electronicFilter;
    }

    public boolean isStationeryFilter() {
        return stationeryFilter;
    }

    public boolean isFoodFilter() {
        return foodFilter;
    }

    public boolean isVehicleFilter() {
        return vehicleFilter;
    }

    public boolean isPcFilter() {
        return pcFilter;
    }

    public boolean isDataStorageFilter() {
        return dataStorageFilter;
    }

    public boolean isPenFilter() {
        return penFilter;
    }

    public boolean isPencilFilter() {
        return pencilFilter;
    }

    public boolean isNoteBookFilter() {
        return noteBookFilter;
    }

    public boolean isCarFilter() {
        return carFilter;
    }

    public boolean isBicycleFilter() {
        return bicycleFilter;
    }

    public Commodity getSeed(int index){
        if(index < Seed.size()) {
            return Seed.get(index);
        }else {
            return null;
        }
    }

    public ArrayList<Commodity> getSeed() {
        return Seed;
    }

    //setters
    public void setAllProductsFilet(boolean allProductsFilet) {
        this.allProductsFilet = allProductsFilet;
    }

    public void setElectronicFilter(boolean electronicFilter) {
        this.electronicFilter = electronicFilter;
    }

    public void setPcFilter(boolean pcFilter) {
        this.pcFilter = pcFilter;
    }

    public void setDataStorageFilter(boolean dataStorageFilter) {
        this.dataStorageFilter = dataStorageFilter;
    }

    public void setStationeryFilter(boolean stationeryFilter) {
        this.stationeryFilter = stationeryFilter;
    }

    public void setPenFilter(boolean penFilter) {
        this.penFilter = penFilter;
    }

    public void setPencilFilter(boolean pencilFilter) {
        this.pencilFilter = pencilFilter;
    }

    public void setNoteBookFilter(boolean noteBookFilter) {
        this.noteBookFilter = noteBookFilter;
    }

    public void setFoodFilter(boolean foodFilter) {
        this.foodFilter = foodFilter;
    }

    public void setVehicleFilter(boolean vehicleFilter) {
        this.vehicleFilter = vehicleFilter;
    }

    public void setCarFilter(boolean carFilter) {
        this.carFilter = carFilter;
    }

    public void setBicycleFilter(boolean bicycleFilter) {
        this.bicycleFilter = bicycleFilter;
    }

    public void setAvaliableFilter(boolean avaliableFilter) {
        this.avaliableFilter = avaliableFilter;
    }

    public void setPriceUPlimit(double priceUPlimit) {
        this.priceUPlimit = priceUPlimit;
    }

    public void setPriceDOWNlimit(double priceDOWNlimit) {
        this.priceDOWNlimit = priceDOWNlimit;
    }

    public void setScoreLimit(float scoreLimit) {
        this.scoreLimit = scoreLimit;
    }
}
