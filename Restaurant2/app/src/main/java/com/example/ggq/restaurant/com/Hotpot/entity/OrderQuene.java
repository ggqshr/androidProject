package com.example.ggq.restaurant.com.Hotpot.entity;

import java.io.Serializable;

public class OrderQuene implements Serializable{

	private static final long serialVersionUID = 1L;
private int queneNumber;
private String businessNumber;
private String customerNumber;
private int orderStatu;
private String orderDate;
private String endDate;
public int getQueneNumber() {
	return queneNumber;
}
public void setQueneNumber(int queneNumber) {
	this.queneNumber = queneNumber;
}
public String getBusinessNumber() {
	return businessNumber;
}
public void setBusinessNumber(String businessNumber) {
	this.businessNumber = businessNumber;
}
public String getCustomerNumber() {
	return customerNumber;
}
public void setCustomerNumber(String customerNumber) {
	this.customerNumber = customerNumber;
}
public int getOrderStatu() {
	return orderStatu;
}
public void setOrderStatu(int orderStatu) {
	this.orderStatu = orderStatu;
}
public String getOrderDate() {
	return orderDate;
}
public void setOrderDate(String orderDate) {
	this.orderDate = orderDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}

}
