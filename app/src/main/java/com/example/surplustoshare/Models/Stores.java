package com.example.surplustoshare.Models;

public class Stores {

    private int storeImg;
    private String openingHours;
    private String salesStartSince;
    private String location;

    public Stores(int storeImg, String openingHours, String salesStartSince, String location) {
        this.storeImg = storeImg;
        this.openingHours = openingHours;
        this.salesStartSince = salesStartSince;
        this.location = location;
    }

    public Stores() {

    }

    public int getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(int storeImg) {
        this.storeImg = storeImg;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getSalesStartSince() {
        return salesStartSince;
    }

    public void setSalesStartSince(String salesStartSince) {
        this.salesStartSince = salesStartSince;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
