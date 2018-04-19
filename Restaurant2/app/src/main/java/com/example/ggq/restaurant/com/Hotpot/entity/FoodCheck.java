package com.example.ggq.restaurant.com.Hotpot.entity;

import java.io.Serializable;

public class FoodCheck implements Serializable{
	private static final long serialVersionUID = 1L;
private int checkNumber;
private String businessNumber;
private String customerNumber;
private String foodNumber;
private int foodNum;
private float checkPrice;
private String checkDate;

public int getCheckNumber() {
	return checkNumber;
}
public void setCheckNumber(int checkNumber) {
	this.checkNumber = checkNumber;
}
public String getBusinessNumber() {
	return businessNumber;
}
public void setBusinessNumber(String businessNumber) {
	this.businessNumber = businessNumber;
}
public String getFoodNumber() {
	return foodNumber;
}
public void setFoodNumber(String foodNumber) {
	this.foodNumber = foodNumber;
}
public int getFoodNum() {
	return foodNum;
}
public void setFoodNum(int foodNum) {
	this.foodNum = foodNum;
}
public float getCheckPrice() {
	return checkPrice;
}
public void setCheckPrice(float checkPrice) {
	this.checkPrice = checkPrice;
}
public String getCheckDate() {
	return checkDate;
}
public void setCheckDate(String checkDate) {
	this.checkDate = checkDate;
}
public String getCustomerNumber() {
	return customerNumber;
}
public void setCustomerNumber(String customerNumber) {
	this.customerNumber = customerNumber;
}

}
