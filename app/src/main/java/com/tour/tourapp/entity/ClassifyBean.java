package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/25.
 * 分类--实体
 */

public class ClassifyBean {
    List<ShopGood> shopGoods;//商品信息列表

    List<GoodsClassify> goodsClassify;//商品的分类状态列表

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
