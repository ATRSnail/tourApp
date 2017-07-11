package com.tour.tourapp.entity;

/**
 * Created by ATRSnail on 2017/5/22.
 * 用户收货地址解析
 */

public class UserAddBean {
    @Override
    public String toString() {
        return "UserAddBean{" +
                "districtAdds='" + districtAdds + '\'' +
                ", id=" + id +
                ", utime=" + utime +
                ", provinceAdds='" + provinceAdds + '\'' +
                ", rePhone='" + rePhone + '\'' +
                ", defaultAdds=" + defaultAdds +
                ", adds='" + adds + '\'' +
                ", cityAdds='" + cityAdds + '\'' +
                ", useFre=" + useFre +
                ", ctime=" + ctime +
                ", appuserId=" + appuserId +
                ", reName='" + reName + '\'' +
                '}';
    }

    /**
     * districtAdds : 武昌区
     * id : 8
     * utime : null
     * provinceAdds : 湖北省
     * rePhone : 13568958645
     * defaultAdds : 0
     * adds : 湖北省武汉市武昌区光谷大道光谷坐标城
     * cityAdds : 武汉市
     * useFre : null
     * ctime : 1495185947000
     * appuserId : 9
     * reName : 小小苹果
     */

    private String districtAdds;
    private int id;
    private Object utime;
    private String provinceAdds;
    private String rePhone;
    private int defaultAdds;
    private String adds;
    private String cityAdds;
    private Object useFre;
    private long ctime;
    private int appuserId;
    private String reName;

    public String getDistrictAdds() {
        return districtAdds;
    }

    public void setDistrictAdds(String districtAdds) {
        this.districtAdds = districtAdds;
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

    public String getProvinceAdds() {
        return provinceAdds;
    }

    public void setProvinceAdds(String provinceAdds) {
        this.provinceAdds = provinceAdds;
    }

    public String getRePhone() {
        return rePhone;
    }

    public void setRePhone(String rePhone) {
        this.rePhone = rePhone;
    }

    public int getDefaultAdds() {
        return defaultAdds;
    }

    public void setDefaultAdds(int defaultAdds) {
        this.defaultAdds = defaultAdds;
    }

    public String getAdds() {
        return adds;
    }

    public void setAdds(String adds) {
        this.adds = adds;
    }

    public String getCityAdds() {
        return cityAdds;
    }

    public void setCityAdds(String cityAdds) {
        this.cityAdds = cityAdds;
    }

    public Object getUseFre() {
        return useFre;
    }

    public void setUseFre(Object useFre) {
        this.useFre = useFre;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getAppuserId() {
        return appuserId;
    }

    public void setAppuserId(int appuserId) {
        this.appuserId = appuserId;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }
}
