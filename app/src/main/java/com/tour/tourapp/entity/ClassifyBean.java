package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/25.
 */

public class ClassifyBean {
    List<ShopGood> shopGoods;

    List<GoodsClassify> goodsClassify;

    public List<ShopGood> getShopGoods() {
        return shopGoods;
    }

    public void setShopGoods(List<ShopGood> shopGoods) {
        this.shopGoods = shopGoods;
    }

    public List<GoodsClassify> getGoodsClassify() {
        return goodsClassify;
    }

    public void setGoodsClassify(List<GoodsClassify> goodsClassify) {
        this.goodsClassify = goodsClassify;
    }

    @Override
    public String toString() {
        return "ClassifyBean{" +
                "shopGoods=" + shopGoods +
                ", goodsClassify=" + goodsClassify +
                '}';
    }
}
