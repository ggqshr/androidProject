package com.example.ggq.restaurantfin.entity;

import java.io.Serializable;

public class Combo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String comboNumber;
    private String comboName;
    private String comboPhoto;
    private String hotpotSoup;
    private int noodlesNum;
    private int meatNum;
    private int vegetableNum;
    private int soupNum;
    private float comboPrice;
    private int comboSum;
    private float comboScore;

    public Combo() {
        // TODO Auto-generated constructor stub
    }

    public float getComboScore() {
        return comboScore;
    }

    public void setComboScore(float comboScore) {
        this.comboScore = comboScore;
    }

    public String getComboPhoto() {
        return comboPhoto;
    }

    public void setComboPhoto(String comboPhoto) {
        this.comboPhoto = comboPhoto;
    }

    public String getHotpotSoup() {
        return hotpotSoup;
    }

    public void setHotpotSoup(String hotpotSoup) {
        this.hotpotSoup = hotpotSoup;
    }

    public int getComboSum() {
        return comboSum;
    }

    public void setComboSum(int comboSum) {
        this.comboSum = comboSum;
    }

    public String getComboNumber() {
        return comboNumber;
    }

    public void setComboNumber(String comboNumber) {
        this.comboNumber = comboNumber;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public int getNoodlesNum() {
        return noodlesNum;
    }

    public void setNoodlesNum(int noodlesNum) {
        this.noodlesNum = noodlesNum;
    }

    public int getMeatNum() {
        return meatNum;
    }

    public void setMeatNum(int meatNum) {
        this.meatNum = meatNum;
    }

    public int getVegetableNum() {
        return vegetableNum;
    }

    public void setVegetableNum(int vegetableNum) {
        this.vegetableNum = vegetableNum;
    }

    public int getSoupNum() {
        return soupNum;
    }

    public void setSoupNum(int soupNum) {
        this.soupNum = soupNum;
    }

    public float getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(float comboPrice) {
        this.comboPrice = comboPrice;
    }


}
