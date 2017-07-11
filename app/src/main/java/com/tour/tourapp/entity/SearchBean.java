package com.tour.tourapp.entity;

import java.util.List;



public class SearchBean {
    private List<ShopBean> Shops;

    private List<GoodBean> goods;

    public List<GoodBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodBean> goods) {
        this.goods = goods;
    }

    public List<ShopBean> getShops() {
        return Shops;
    }

    public void setShops(List<ShopBean> shops) {
        Shops = shops;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "Shops=" + Shops +
                ", goods=" + goods +
                '}';
    }
}
