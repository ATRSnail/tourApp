package com.tour.tourapp.entity;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/4/13
 */

public class ShopsBean {
    private List<ShopBean> Shops;

    public List<ShopBean> getShops() {
        return Shops;
    }

    public void setShops(List<ShopBean> shops) {
        Shops = shops;
    }

    @Override
    public String toString() {
        return "ShopsBean{" +
                "Shops=" + Shops +
                '}';
    }
}
