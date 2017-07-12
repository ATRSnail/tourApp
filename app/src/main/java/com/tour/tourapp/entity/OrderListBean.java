package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/7/12.
 * 订单 列表
 */

public class OrderListBean {
    List<OrderBean> order;

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderListBean{" +
                "order=" + order +
                '}';
    }
}
