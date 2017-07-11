package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/25.
 */

public class GoodDetailBean {
    List<ShopGood> shopGoods;

    public List<ShopGood> getShopGoods() {
        return shopGoods;
    }

    public void setShopGoods(List<ShopGood> shopGoods) {
        this.shopGoods = shopGoods;
    }

    @Override
    public String toString() {
        return "GoodDetailBean{" +
                "shopGoods=" + shopGoods +
                '}';
    }
}
