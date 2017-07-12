package com.tour.tourapp.entity;

/**
 *   商品详情的att字段
 */

public class AttBean {

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
