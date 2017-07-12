package com.tour.tourapp.entity;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/2
 */

public class GoodsObjectBean {

    private List<GoodsDetailBean> goods;

    public List<GoodsDetailBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsDetailBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "GoodsObjectBean{" +
                "goods=" + goods +
                '}';
    }
}
