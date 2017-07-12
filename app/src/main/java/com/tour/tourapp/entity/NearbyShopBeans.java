package com.tour.tourapp.entity;

import java.util.List;


/**
 * 附近商铺 列表
 */
public class NearbyShopBeans {
    private List<ShopDetailBean> Shops;

    public List<ShopDetailBean> getShops() {
        return Shops;
    }

    public void setShops(List<ShopDetailBean> shops) {
        Shops = shops;
    }

    @Override
    public String toString() {
        return "NearbyShopBeans{" +
                "Shops=" + Shops +
                '}';
    }
}
