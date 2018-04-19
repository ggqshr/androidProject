package com.example.ggq.restaurantfin.entity;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String customerNumber;
    private String customerPass;
    private String customerName;
    private String customerSex;
    private String customerPhone;
    private float waiterMoney;

    public Customer() {
        // TODO Auto-generated constructor stub
    }

    public Customer(String customerNumber, String customerPass, String customerName, String customerSex, String customerPhone) {
        this.customerNumber = customerNumber;
        this.customerPass = customerPass;
        this.customerName = customerName;
        this.customerSex = customerSex;
        this.customerPhone = customerPhone;
    }

    public float getWaiterMoney() {
        return waiterMoney;
    }

    public void setWaiterMoney(float waiterMoney) {
        this.waiterMoney = waiterMoney;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerPass() {
        return customerPass;
    }

    public void setCustomerPass(String customerPass) {
        this.customerPass = customerPass;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

}
