package com.example.surplustoshare.Models;

public class Order {

    private int id;

    private int FoodImage;

    private String FoodName;

    private String FoodPrice;

    private String FoodDesc;

    public Order(int id, int foodImage, String foodName, String foodPrice, String foodDesc) {
        this.id = id;
        FoodImage = foodImage;
        FoodName = foodName;
        FoodPrice = foodPrice;
        FoodDesc = foodDesc;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodImage() {
        return FoodImage;
    }

    public void setFoodImage(int foodImage) {
        FoodImage = foodImage;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodPrice() {
        return FoodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        FoodPrice = foodPrice;
    }

    public String getFoodDesc() {
        return FoodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        FoodDesc = foodDesc;
    }
}
