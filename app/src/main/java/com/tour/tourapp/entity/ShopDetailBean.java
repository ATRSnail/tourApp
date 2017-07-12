package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/23.
 * 根据商铺id，查询商铺的详细信息  和附近商铺 详情 一致
 * 商铺详情  15个信息
 */

public class ShopDetailBean {

    public ShopDetailBean(String lats, String longs) {
        this.lats = lats;
        this.longs = longs;
    }
    @Override
    public String toString() {
        return "ShopDetailBean{" +
                "uid='" + uid + '\'' +
                ", id=" + id +
                ", utime=" + utime +
                ", shopsRange='" + shopsRange + '\'' +
                ", distance=" + distance +
                ", lats='" + lats + '\'' +
                ", longs='" + longs + '\'' +
                ", state='" + state + '\'' +
                ", shopsType='" + shopsType + '\'' +
                ", ctime=" + ctime +
                ", shopsInfo='" + shopsInfo + '\'' +
                ", shopsAdds='" + shopsAdds + '\'' +
                ", shopsCity='" + shopsCity + '\'' +
                ", shopsName='" + shopsName + '\'' +
                ", shopsAtt=" + shopsAtt +
                '}';
    }

    /**
     * uid : v7EW9amdIgLpkzGo0UpRSwhvJshRXQNp
     * id : 11
     * utime : null
     * shopsRange : 手机数码
     * distance : null
     * lats : 39.99400501
     * longs : 116.379354
     * state : 1
     * shopsAtt : [{"id":22,"utime":null,"attUrl":"/shops/0e92442cd4444b43bb5d602ca1f92290.jpg","attType":"2","shopsId":11,"ctime":1493013973000}]
     * shopsType : 市场类商铺
     * ctime : 1493013973000
     * shopsInfo : 手机数码
     手机数码
     手机数码
     * shopsAdds : 三里屯街道雅秀大厦
     * shopsCity : 北京
     * shopsName : 苏宁易购官方旗舰店
     */

    private String uid;
    private int id;
    private Object utime;
    private String shopsRange;
    private Object distance;
    private String lats;
    private String longs;
    private String state;
    private String shopsType;
    private long ctime;
    private String shopsInfo;
    private String shopsAdds;
    private String shopsCity;
    private String shopsName;
    private List<ShopsAttBean> shopsAtt;

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

    public Object getUtime() {
        return utime;
    }

    public void setUtime(Object utime) {
        this.utime = utime;
    }

    public String getShopsRange() {
        return shopsRange;
    }

    public void setShopsRange(String shopsRange) {
        this.shopsRange = shopsRange;
    }

    public Object getDistance() {
        return distance;
    }

    public void setDistance(Object distance) {
        this.distance = distance;
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

    public String getShopsType() {
        return shopsType;
    }

    public void setShopsType(String shopsType) {
        this.shopsType = shopsType;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
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

    public List<ShopsAttBean> getShopsAtt() {
        return shopsAtt;
    }

    public void setShopsAtt(List<ShopsAttBean> shopsAtt) {
        this.shopsAtt = shopsAtt;
    }


}
