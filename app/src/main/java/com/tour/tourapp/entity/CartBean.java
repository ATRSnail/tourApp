package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/7/12.
 * 购物车 商铺 详情（实体）
 */

public class CartBean {

    @Override
    public String toString() {
        return "CartBean{" +
                "shopsId=" + shopsId +
                ", shopsName='" + shopsName + '\'' +
                ", listCart=" + listCart +
                '}';
    }

    /**
     * 商铺-----商品列表
     * listCart : [{"id":54,"createTime":1495776274000,"goodsColor":null,"userId":9,"goodsPicture":"/shopsGoods/f18fa839fc0a4b62b822b56bf112f624.jpg","goodsPrice":23.8,"quantity":3,"shopsId":13,"goodsID":23,"goodsName":"零食大礼包送女友一箱整箱超大休闲组合生日混装批发小吃吃货进口","shopsName":"丁丁食品专营店"},{"id":55,"createTime":1495776277000,"goodsColor":null,"userId":9,"goodsPicture":"/shopsGoods/f18fa839fc0a4b62b822b56bf112f624.jpg","goodsPrice":23.8,"quantity":1,"shopsId":13,"goodsID":23,"goodsName":"零食大礼包送女友一箱整箱超大休闲组合生日混装批发小吃吃货进口","shopsName":"丁丁食品专营店"},{"id":56,"createTime":1495776280000,"goodsColor":null,"userId":9,"goodsPicture":"/shopsGoods/f18fa839fc0a4b62b822b56bf112f624.jpg","goodsPrice":23.8,"quantity":1,"shopsId":13,"goodsID":23,"goodsName":"零食大礼包送女友一箱整箱超大休闲组合生日混装批发小吃吃货进口","shopsName":"丁丁食品专营店"},{"id":57,"createTime":1495778256000,"goodsColor":null,"userId":9,"goodsPicture":"/shopsGoods/04eac4a968e24d73b556701e660ca8d8.jpg","goodsPrice":16.9,"quantity":2,"shopsId":13,"goodsID":21,"goodsName":"台湾进口零食77松塔千层酥饼干曲奇蜜兰诺12粒","shopsName":"丁丁食品专营店"}]
     * shopsId : 13
     * shopsName : 丁丁食品专营店
     */

    private int shopsId;
    private String shopsName;
    private List<ListCartBean> listCart;

    public int getShopsId() {
        return shopsId;
    }

    public void setShopsId(int shopsId) {
        this.shopsId = shopsId;
    }

    public String getShopsName() {
        return shopsName;
    }

    public void setShopsName(String shopsName) {
        this.shopsName = shopsName;
    }

    public List<ListCartBean> getListCart() {
        return listCart;
    }

    public void setListCart(List<ListCartBean> listCart) {
        this.listCart = listCart;
    }

    public static class ListCartBean {

        @Override
        public String toString() {
            return "ListCartBean{" +
                    "id=" + id +
                    ", createTime=" + createTime +
                    ", goodsColor=" + goodsColor +
                    ", userId=" + userId +
                    ", goodsPicture='" + goodsPicture + '\'' +
                    ", goodsPrice=" + goodsPrice +
                    ", quantity=" + quantity +
                    ", shopsId=" + shopsId +
                    ", goodsID=" + goodsID +
                    ", goodsName='" + goodsName + '\'' +
                    ", shopsName='" + shopsName + '\'' +
                    '}';
        }

        /**
         * id : 54
         * createTime : 1495776274000
         * goodsColor : null
         * userId : 9
         * goodsPicture : /shopsGoods/f18fa839fc0a4b62b822b56bf112f624.jpg
         * goodsPrice : 23.8
         * quantity : 3
         * shopsId : 13
         * goodsID : 23
         * goodsName : 零食大礼包送女友一箱整箱超大休闲组合生日混装批发小吃吃货进口
         * shopsName : 丁丁食品专营店
         */

        private int id;
        private long createTime;
        private Object goodsColor;
        private int userId;
        private String goodsPicture;
        private double goodsPrice;
        private int quantity;
        private int shopsId;
        private int goodsID;
        private String goodsName;
        private String shopsName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getGoodsColor() {
            return goodsColor;
        }

        public void setGoodsColor(Object goodsColor) {
            this.goodsColor = goodsColor;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getGoodsPicture() {
            return goodsPicture;
        }

        public void setGoodsPicture(String goodsPicture) {
            this.goodsPicture = goodsPicture;
        }

        public double getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(double goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getShopsId() {
            return shopsId;
        }

        public void setShopsId(int shopsId) {
            this.shopsId = shopsId;
        }

        public int getGoodsID() {
            return goodsID;
        }

        public void setGoodsID(int goodsID) {
            this.goodsID = goodsID;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getShopsName() {
            return shopsName;
        }

        public void setShopsName(String shopsName) {
            this.shopsName = shopsName;
        }
    }
}
