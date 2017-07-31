package com.tour.tourapp.entity;

import java.util.List;


/**
 * 搜索 商铺商品 列表
 */
public class SearchBeans {
    private List<ShopDetailBean> shops;

    private List<GoodsDetailBean> goods;

    public List<ShopDetailBean> getShops() {
        return shops;
    }

    public void setShops(List<ShopDetailBean> shops) {
        shops = shops;
    }

    public List<GoodsDetailBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsDetailBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "SearchBeans{" +
                "shops=" + shops +
                ", goods=" + goods +
                '}';
    }
}
