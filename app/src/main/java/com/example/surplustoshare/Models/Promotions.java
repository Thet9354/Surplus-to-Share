package com.example.surplustoshare.Models;

public class Promotions {

    private int promoImg;
    private String caption;

    public Promotions(int promoImg, String caption) {
        this.promoImg = promoImg;
        this.caption = caption;
    }

    public Promotions() {

    }

    public int getPromoImg() {
        return promoImg;
    }

    public void setPromoImg(int promoImg) {
        this.promoImg = promoImg;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
