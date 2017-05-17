package com.tour.tourapp.entity;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public class ShopDetailBean {

    private ShopBean shops;

    private List<GoodBean> goods;

    public ShopBean getShops() {
        return shops;
    }

    public void setShops(ShopBean shops) {
        this.shops = shops;
    }

    public List<GoodBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "ShopDetailBean{" +
                "shops=" + shops +
                ", goods=" + goods +
                '}';
    }
}
