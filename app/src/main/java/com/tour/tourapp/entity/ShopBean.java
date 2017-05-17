package com.tour.tourapp.entity;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/4/13
 */

public class ShopBean {


    /**
     * uid : 8
     * id : 8
     * shopsRange : 8
     * lats : 39.9927392
     * longs : 116.37924671
     * state : 1
     * uTime : null
     * shopsType : 8
     * shopsInfo : 8
     * shopsAdds : 8
     * cTime : null
     * shopsCity : 8
     * shopsName : 8
     */

    private String uid;
    private int id;
    private String shopsRange;
    private String lats;
    private String longs;
    private String state;
    private Object uTime;
    private String shopsType;
    private String shopsInfo;
    private String shopsAdds;
    private Object cTime;
    private String shopsCity;
    private String shopsName;
    private List<ShopAttBean> shopsAtt;

    public ShopBean(String lats, String longs) {
        this.lats = lats;
        this.longs = longs;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopsRange() {
        return shopsRange;
    }

    public void setShopsRange(String shopsRange) {
        this.shopsRange = shopsRange;
    }

    public String getLats() {
        return lats;
    }

    public void setLats(String lats) {
        this.lats = lats;
    }

    public String getLongs() {
        return longs;
    }

    public void setLongs(String longs) {
        this.longs = longs;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getuTime() {
        return uTime;
    }

    public void setuTime(Object uTime) {
        this.uTime = uTime;
    }

    public String getShopsType() {
        return shopsType;
    }

    public void setShopsType(String shopsType) {
        this.shopsType = shopsType;
    }

    public String getShopsInfo() {
        return shopsInfo;
    }

    public void setShopsInfo(String shopsInfo) {
        this.shopsInfo = shopsInfo;
    }

    public String getShopsAdds() {
        return shopsAdds;
    }

    public void setShopsAdds(String shopsAdds) {
        this.shopsAdds = shopsAdds;
    }

    public Object getcTime() {
        return cTime;
    }

    public void setcTime(Object cTime) {
        this.cTime = cTime;
    }

    public String getShopsCity() {
        return shopsCity;
    }

    public void setShopsCity(String shopsCity) {
        this.shopsCity = shopsCity;
    }

    public String getShopsName() {
        return shopsName;
    }

    public void setShopsName(String shopsName) {
        this.shopsName = shopsName;
    }

    public List<ShopAttBean> getShopsAtt() {
        return shopsAtt;
    }

    public void setShopsAtt(List<ShopAttBean> shopsAtt) {
        this.shopsAtt = shopsAtt;
    }

    @Override
    public String toString() {
        return "ShopBean{" +
                "uid='" + uid + '\'' +
                ", id=" + id +
                ", shopsRange='" + shopsRange + '\'' +
                ", lats='" + lats + '\'' +
                ", longs='" + longs + '\'' +
                ", state='" + state + '\'' +
                ", uTime=" + uTime +
                ", shopsType='" + shopsType + '\'' +
                ", shopsInfo='" + shopsInfo + '\'' +
                ", shopsAdds='" + shopsAdds + '\'' +
                ", cTime=" + cTime +
                ", shopsCity='" + shopsCity + '\'' +
                ", shopsName='" + shopsName + '\'' +
                ", shopsAtt=" + shopsAtt +
                '}';
    }
}
