package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/7/12.
 * 购物车 商铺列表（实体）
 */

public class CartShopBean {

    List<CartBean> cart;

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartShopBean{" +
                "cart=" + cart +
                '}';
    }
}
