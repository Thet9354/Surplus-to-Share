package com.example.surplustoshare.Models;

public class JustIn {

    private int foodImg;
    private String foodTitle;
    private String foodPrice;

    public JustIn(int foodImg, String foodTitle, String foodPrice) {
        this.foodImg = foodImg;
        this.foodTitle = foodTitle;
        this.foodPrice = foodPrice;
    }

    public JustIn() {

    }

    public int getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }
}
