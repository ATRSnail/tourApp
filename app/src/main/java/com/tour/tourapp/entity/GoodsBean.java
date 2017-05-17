package com.tour.tourapp.entity;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/2
 */

public class GoodsBean {

    private List<GoodBean> goods;

    public List<GoodBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "goods=" + goods +
                '}';
    }
}
