package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/25.
 */

public class GoodDetailBeans {
    List<GoodsDetailBean> shopGoods;

    public List<GoodsDetailBean> getShopGoods() {
        return shopGoods;
    }

    public void setShopGoods(List<GoodsDetailBean> shopGoods) {
        this.shopGoods = shopGoods;
    }

    @Override
    public String toString() {
        return "GoodDetailBeans{" +
                "shopGoods=" + shopGoods +
                '}';
    }
}
