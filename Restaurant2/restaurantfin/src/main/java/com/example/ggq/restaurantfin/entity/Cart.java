package com.example.ggq.restaurantfin.entity;

/**
 * Created by ggq on 2017/5/26.
 */

public class Cart {
    private String cartName;
    private String cartNumber;
    private String cartPhoto;
    private int cartSum;
    private String cartPrice;

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    public String getCartPhoto() {
        return cartPhoto;
    }

    public void setCartPhoto(String cartPhoto) {
        this.cartPhoto = cartPhoto;
    }

    public int getCartSum() {
        return cartSum;
    }

    public void setCartSum(int cartSum) {
        this.cartSum = cartSum;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }
}
