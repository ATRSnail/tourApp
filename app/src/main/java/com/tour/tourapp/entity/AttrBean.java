package com.tour.tourapp.entity;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public class AttrBean {

    /**
     * id : 15
     * utime : null
     * goodsId : 4
     * attUrl : rqerq
     * attType : 0001
     * ctime : null
     */

    private int id;
    private Object utime;
    private int goodsId;
    private String attUrl;
    private String attType;
    private Object ctime;

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

    public Object getCtime() {
        return ctime;
    }

    public void setCtime(Object ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "AttrBean{" +
                "id=" + id +
                ", utime=" + utime +
                ", goodsId=" + goodsId +
                ", attUrl='" + attUrl + '\'' +
                ", attType='" + attType + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
