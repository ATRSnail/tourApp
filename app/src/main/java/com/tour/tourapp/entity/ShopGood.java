package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/25.
 * 商品详情
 */

public class ShopGood {


    @Override
    public String toString() {
        return "ShopGood{" +
                "goodsInfo='" + goodsInfo + '\'' +
                ", id=" + id +
                ", utime=" + utime +
                ", stock=" + stock +
                ", state='" + state + '\'' +
                ", shopsId=" + shopsId +
                ", ctime=" + ctime +
                ", goodsName='" + goodsName + '\'' +
                ", priceS=" + priceS +
                ", goodsType='" + goodsType + '\'' +
                ", att=" + att +
                ", comment=" + comment +
                '}';
    }

    /**
     * goodsInfo : </p>
     * id : 22
     * utime : 1495594505000
     * stock : 94
     * att : [{"id":24,"utime":1495177489000,"goodsId":22,"attUrl":"/shopsGoods/8c40732f9be64034960d8d10e17ae3a6.jpg","attType":"0001","ctime":1495177489000},{"id":28,"utime":1495177888000,"goodsId":22,"attUrl":"/shopsGoods/d488d37d917d42efb1a85d54959ce32e.jpg","attType":"0002","ctime":1495177888000}]
     * state : 1
     * comment : []
     * shopsId : 13
     * ctime : 1495177489000
     * goodsName : 泰国进口零食品 小老板脆紫菜即食烤海苔卷原味辛辣4盒 bigroll
     * priceS : 44.8
     * goodsType : 0007
     */



    private String goodsInfo;
    private int id;
    private long utime;
    private int stock;
    private String state;
    private int shopsId;
    private long ctime;
    private String goodsName;
    private double priceS;
    private String goodsType;
    private List<AttBean> att;
    private List<?> comment;

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(long utime) {
        this.utime = utime;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getShopsId() {
        return shopsId;
    }

    public void setShopsId(int shopsId) {
        this.shopsId = shopsId;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPriceS() {
        return priceS;
    }

    public void setPriceS(double priceS) {
        this.priceS = priceS;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public List<AttBean> getAtt() {
        return att;
    }

    public void setAtt(List<AttBean> att) {
        this.att = att;
    }

    public List<?> getComment() {
        return comment;
    }

    public void setComment(List<?> comment) {
        this.comment = comment;
    }

    public static class AttBean {
        @Override
        public String toString() {
            return "AttBean{" +
                    "id=" + id +
                    ", utime=" + utime +
                    ", goodsId=" + goodsId +
                    ", attUrl='" + attUrl + '\'' +
                    ", attType='" + attType + '\'' +
                    ", ctime=" + ctime +
                    '}';
        }

        /**
         * id : 24
         * utime : 1495177489000
         * goodsId : 22
         * attUrl : /shopsGoods/8c40732f9be64034960d8d10e17ae3a6.jpg
         * attType : 0001
         * ctime : 1495177489000
         */




        private int id;
        private long utime;
        private int goodsId;
        private String attUrl;
        private String attType;
        private long ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getUtime() {
            return utime;
        }

        public void setUtime(long utime) {
            this.utime = utime;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getAttUrl() {
            return attUrl;
        }

        public void setAttUrl(String attUrl) {
            this.attUrl = attUrl;
        }

        public String getAttType() {
            return attType;
        }

        public void setAttType(String attType) {
            this.attType = attType;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }
    }
}
