package com.tour.tourapp.entity;

/**
 * Created by ATRSnail on 2017/5/23.
 */

public class StoreDetailObj {
    StoreDetail shop;

    public StoreDetail getShop() {
        return shop;
    }

    public void setShop(StoreDetail shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "StoreDetailObj{" +
                "shop=" + shop +
                '}';
    }
}
