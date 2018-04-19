package com.example.ggq.restaurantfin.entity;

import java.io.Serializable;

public class Business implements Serializable {
    private static final long serialVersionUID = 1L;
    private String businessNumber;
    private String customerNumber;
    private String businessType;
    private String foodNumber;
    private String comboNumber;
    private int foodSum;
    private float businessMoney;
    private String businessDate;
    private int businessScore;
    private String comboName;
    private String comboPhoto;

    public Business() {
        // TODO Auto-generated constructor stub
    }

    public String getComboPhoto() {
        return comboPhoto;
    }

    public void setComboPhoto(String comboPhoto) {
        this.comboPhoto = comboPhoto;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(String foodNumber) {
        this.foodNumber = foodNumber;
    }

    public String getComboNumber() {
        return comboNumber;
    }

    public void setComboNumber(String comboNumber) {
        this.comboNumber = comboNumber;
    }

    public int getFoodSum() {
        return foodSum;
    }

    public void setFoodSum(int foodSum) {
        this.foodSum = foodSum;
    }

    public float getBusinessMoney() {
        return businessMoney;
    }

    public void setBusinessMoney(float businessMoney) {
        this.businessMoney = businessMoney;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getBusinessScore() {
        return businessScore;
    }

    public void setBusinessScore(int businessScore) {
        this.businessScore = businessScore;
    }

}
