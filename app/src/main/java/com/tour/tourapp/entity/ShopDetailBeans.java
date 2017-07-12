package com.tour.tourapp.entity;

/**
 * Created by ATRSnail on 2017/5/23.
 * 根据商铺id，查询商铺的详细信息
 */

public class ShopDetailBeans {
    ShopDetailBean shop;

    public ShopDetailBean getShop() {
        return shop;
    }

    public void setShop(ShopDetailBean shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ShopDetailBeans{" +
                "shop=" + shop +
                '}';
    }
}
