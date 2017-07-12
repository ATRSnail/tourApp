package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/7/12.
 * 订单 详情
 */

public class OrderBean {

    @Override
    public String toString() {
        return "OrderBean{" +
                "utime=" + utime +
                ", orderState='" + orderState + '\'' +
                ", provinceAdds='" + provinceAdds + '\'' +
                ", rePhone='" + rePhone + '\'' +
                ", cityAdds='" + cityAdds + '\'' +
                ", ctime=" + ctime +
                ", appuserId=" + appuserId +
                ", districtAdds='" + districtAdds + '\'' +
                ", id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", nickName=" + nickName +
                ", shopName='" + shopName + '\'' +
                ", printTemplate=" + printTemplate +
                ", shipperCode=" + shipperCode +
                ", shopsId=" + shopsId +
                ", logisticCode=" + logisticCode +
                ", reAdds='" + reAdds + '\'' +
                ", reName='" + reName + '\'' +
                ", priceS=" + priceS +
                ", childOrder=" + childOrder +
                '}';
    }

    /**
     * utime : null
     * orderState : 1
     * provinceAdds : 北京市
     * rePhone : 13568958646
     * cityAdds : 北京市
     * ctime : 1495790126000
     * appuserId : 9
     * districtAdds : 全市
     * id : 48
     * orderNo : 2017052605151495790116611119
     * nickName : null
     * shopName : 苏宁易购官方旗舰店
     * printTemplate : null
     * shipperCode : null
     * 子订单
     * childOrder : [{"id":27,"utime":null,"goodsId":14,"goodsAttUrl":"/shopsGoods/0a1b7890197b438884f9182f65382422.jpg","hows":10,"shopsId":11,"ctime":1495790130000,"goodsName":"【限时直降】Apple/苹果 iPhone 7 32G 全网通4G智能手机原封国行","orderId":48,"priceS":4888}]
     * shopsId : 11
     * logisticCode : null
     * reAdds : 北京市北京市全市北京市|北京市|全市|光谷大道光谷坐标城
     * reName : 小小苹果
     * priceS : 48880
     */

    private Object utime;
    private String orderState;
    private String provinceAdds;
    private String rePhone;
    private String cityAdds;
    private long ctime;
    private int appuserId;
    private String districtAdds;
    private int id;
    private String orderNo;
    private Object nickName;
    private String shopName;
    private Object printTemplate;
    private Object shipperCode;
    private int shopsId;
    private Object logisticCode;
    private String reAdds;
    private String reName;
    private int priceS;
    private List<ChildOrderBean> childOrder;

    public Object getUtime() {
        return utime;
    }

    public void setUtime(Object utime) {
        this.utime = utime;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
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

    public String getCityAdds() {
        return cityAdds;
    }

    public void setCityAdds(String cityAdds) {
        this.cityAdds = cityAdds;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Object getNickName() {
        return nickName;
    }

    public void setNickName(Object nickName) {
        this.nickName = nickName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Object getPrintTemplate() {
        return printTemplate;
    }

    public void setPrintTemplate(Object printTemplate) {
        this.printTemplate = printTemplate;
    }

    public Object getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(Object shipperCode) {
        this.shipperCode = shipperCode;
    }

    public int getShopsId() {
        return shopsId;
    }

    public void setShopsId(int shopsId) {
        this.shopsId = shopsId;
    }

    public Object getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(Object logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getReAdds() {
        return reAdds;
    }

    public void setReAdds(String reAdds) {
        this.reAdds = reAdds;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }

    public int getPriceS() {
        return priceS;
    }

    public void setPriceS(int priceS) {
        this.priceS = priceS;
    }

    public List<ChildOrderBean> getChildOrder() {
        return childOrder;
    }

    public void setChildOrder(List<ChildOrderBean> childOrder) {
        this.childOrder = childOrder;
    }

    public static class ChildOrderBean {
        @Override
        public String toString() {
            return "ChildOrderBean{" +
                    "id=" + id +
                    ", utime=" + utime +
                    ", goodsId=" + goodsId +
                    ", goodsAttUrl='" + goodsAttUrl + '\'' +
                    ", hows=" + hows +
                    ", shopsId=" + shopsId +
                    ", ctime=" + ctime +
                    ", goodsName='" + goodsName + '\'' +
                    ", orderId=" + orderId +
                    ", priceS=" + priceS +
                    '}';
        }

        /**
         * id : 27
         * utime : null
         * goodsId : 14
         * goodsAttUrl : /shopsGoods/0a1b7890197b438884f9182f65382422.jpg
         * hows : 10
         * shopsId : 11
         * ctime : 1495790130000
         * goodsName : 【限时直降】Apple/苹果 iPhone 7 32G 全网通4G智能手机原封国行
         * orderId : 48
         * priceS : 4888
         */

        private int id;
        private Object utime;
        private int goodsId;
        private String goodsAttUrl;
        private int hows;
        private int shopsId;
        private long ctime;
        private String goodsName;
        private int orderId;
        private int priceS;

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

        public String getGoodsAttUrl() {
            return goodsAttUrl;
        }

        public void setGoodsAttUrl(String goodsAttUrl) {
            this.goodsAttUrl = goodsAttUrl;
        }

        public int getHows() {
            return hows;
        }

        public void setHows(int hows) {
            this.hows = hows;
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

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getPriceS() {
            return priceS;
        }

        public void setPriceS(int priceS) {
            this.priceS = priceS;
        }
    }
}
