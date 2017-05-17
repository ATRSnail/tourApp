package com.tour.tourapp.entity;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/2
 */

public class GoodBean {


    /**
     * goodsInfo : 尔特如娱乐费锁国福多敷
     * id : 1
     * utime : null
     * stock : null
     * state : 1
     * shopsId : 8
     * ctime : null
     * goodsName : 华为1T
     * priceS : 5.8
     * goodsType : 472
     */

    private String goodsInfo;
    private int id;
    private Object utime;
    private Object stock;
    private String state;
    private int shopsId;
    private Object ctime;
    private String goodsName;
    private double priceS;
    private String goodsType;
    private List<AttrBean> att;

    public List<AttrBean> getAtt() {
        return att;
    }

    public void setAtt(List<AttrBean> att) {
        this.att = att;
    }

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

    public Object getUtime() {
        return utime;
    }

    public void setUtime(Object utime) {
        this.utime = utime;
    }

    public Object getStock() {
        return stock;
    }

    public void setStock(Object stock) {
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

    public Object getCtime() {
        return ctime;
    }

    public void setCtime(Object ctime) {
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

    @Override
    public String toString() {
        return "GoodBean{" +
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
                '}';
    }
}
