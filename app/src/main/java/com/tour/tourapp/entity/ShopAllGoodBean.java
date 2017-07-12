package com.tour.tourapp.entity;

import java.util.List;

/**
 * 商铺下所有商品
 */

public class ShopAllGoodBean {

    private ShopBean shops;//商铺详情

    private List<GoodsBean> goods;//商铺下商品列表

    public ShopBean getShops() {
        return shops;
    }

    public void setShops(ShopBean shops) {
        this.shops = shops;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "ShopAllGoodBean{" +
                "shops=" + shops +
                ", goods=" + goods +
                '}';
    }
}
