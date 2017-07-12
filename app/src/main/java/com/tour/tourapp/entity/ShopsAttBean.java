package com.tour.tourapp.entity;

/**
 * 商铺详情 的属性
 */

public class ShopsAttBean {
    @Override
    public String toString() {
        return "ShopsAttBean{" +
                "id=" + id +
                ", utime=" + utime +
                ", attUrl='" + attUrl + '\'' +
                ", attType='" + attType + '\'' +
                ", shopsId=" + shopsId +
                ", ctime=" + ctime +
                '}';
    }

    /**
     * id : 10
     * utime : null
     * attUrl : /shops/03.jpg
     * attType : 2
     * shopsId : 9
     * ctime : null
     */

    private int id;
    private Object utime;
    private String attUrl;
    private String attType;
    private int shopsId;
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
}
