package com.tour.tourapp.entity;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/9
 */

public class ShopAttBean {


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
