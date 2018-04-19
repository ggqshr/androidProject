package com.example.ggq.restaurantfin.entity;

import java.io.Serializable;

public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    private String foodNumber;
    private String foodName;
    private String foodPhoto;
    private String foodType;
    private Float foodPrice;
    private int foodNum = 1;
    private int foodBanlance;

    public int getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(int foodNum) {
        this.foodNum = foodNum;
    }

    public String getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(String foodNumber) {
        this.foodNumber = foodNumber;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPhoto() {
        return foodPhoto;
    }

    public void setFoodPhoto(String foodPhoto) {
        this.foodPhoto = foodPhoto;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodBanlance() {
        return foodBanlance;
    }

    public void setFoodBanlance(int foodBanlance) {
        this.foodBanlance = foodBanlance;
    }

}
