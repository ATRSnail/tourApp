package com.tour.tourapp.entity;

/**
 * 根据商铺id查询该商铺下所有商品
 * 包含的商铺 16个信息
 */

public class ShopBean {


    @Override
    public String toString() {
        return "ShopBean{" +
                "uid='" + uid + '\'' +
                ", utime=" + utime +
                ", shopsRange='" + shopsRange + '\'' +
                ", lats='" + lats + '\'' +
                ", cityName='" + cityName + '\'' +
                ", state='" + state + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", shopsType='" + shopsType + '\'' +
                ", ctime=" + ctime +
                ", shopsAdds='" + shopsAdds + '\'' +
                ", shopsInfo='" + shopsInfo + '\'' +
                ", shopsName='" + shopsName + '\'' +
                ", id=" + id +
                ", areaName='" + areaName + '\'' +
                ", longs='" + longs + '\'' +
                ", shopsCity='" + shopsCity + '\'' +
                '}';
    }



    /**
     * uid : v7EW9amdIgLpkzGo0UpRSwhvJshRXQNp
     * utime : null
     * shopsRange : 手机数码
     * lats : 39.99400501
     * cityName : 北京市
     * state : 1
     * provinceName : 北京市
     * shopsType : 0002
     * ctime : 1493013973000
     * shopsAdds : 三里屯街道雅秀大厦
     * shopsInfo : 手机数码
     手机数码
     手机数码
     * shopsName : 苏宁易购官方旗舰店
     * id : 11
     * areaName : 朝阳区
     * longs : 116.379354
     * shopsCity : 0001
     */

    private String uid;
    private Object utime;
    private String shopsRange;
    private String lats;
    private String cityName;
    private String state;
    private String provinceName;
    private String shopsType;
    private long ctime;
    private String shopsAdds;
    private String shopsInfo;
    private String shopsName;
    private int id;
    private String areaName;
    private String longs;
    private String shopsCity;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getLats() {
        return lats;
    }

    public void setLats(String lats) {
        this.lats = lats;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public String getShopsAdds() {
        return shopsAdds;
    }

    public void setShopsAdds(String shopsAdds) {
        this.shopsAdds = shopsAdds;
    }

    public String getShopsInfo() {
        return shopsInfo;
    }

    public void setShopsInfo(String shopsInfo) {
        this.shopsInfo = shopsInfo;
    }

    public String getShopsName() {
        return shopsName;
    }

    public void setShopsName(String shopsName) {
        this.shopsName = shopsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getLongs() {
        return longs;
    }

    public void setLongs(String longs) {
        this.longs = longs;
    }

    public String getShopsCity() {
        return shopsCity;
    }

    public void setShopsCity(String shopsCity) {
        this.shopsCity = shopsCity;
    }
}
