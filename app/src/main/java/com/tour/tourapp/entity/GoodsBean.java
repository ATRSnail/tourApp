package com.tour.tourapp.entity;

import java.util.List;

/**
 * 商品详情
 */

public class GoodsBean {
    @Override
    public String toString() {
        return "GoodsBean{" +
                "goodsInfo='" + goodsInfo + '\'' +
                ", utime=" + utime +
                ", state='" + state + '\'' +
                ", ctime=" + ctime +
                ", shopsName=" + shopsName +
                ", id=" + id +
                ", stock=" + stock +
                ", isCollection=" + isCollection +
                ", comment=" + comment +
                ", shopsId=" + shopsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", priceS=" + priceS +
                ", att=" + att +
                '}';
    }

    /**
     * goodsInfo : <p><br/></p><p style="margin-top: 0px; margin-bottom: 0px; white-space: normal; padding: 0px; line-height: 1.4;"><a href="https://suning.tmall.com/campaign-3741-244.htm?scene=taobao_shop" target="_blank" style="margin: 0px; padding: 0px; text-decoration: none; color: rgb(41, 83, 166); outline: 0px; border-width: 0px;"><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014228691057462.jpg" alt=" 蜜蜂节--790.jpg" class="img-ks-lazyload"/></a></p><p style="white-space: normal;"><br/></p><p style="white-space: normal;"><span style="text-decoration: line-through;"><strong style="margin: 0px; padding: 0px;"></strong></span></p><p style="white-space: normal;"><a class="jdb abs aCVSa-Vi9b" href="https://detail.tmall.com/item.htm?spm=a220o.1000855.w14156616-16215432758.1.IU2CqM&id=538869984042&scene=taobao_shop" target="_blank" style="margin: 0px; padding: 0px; text-decoration: none; color: rgb(153, 153, 153); outline: 0px; position: absolute; border: none; display: block; width: 791px; height: 197px; top: 0px; left: 0px; z-index: 10;"></a></p><p style="white-space: normal;"><a href="https://suning.tmall.com/p/rd421910.htm" target="_blank" style="margin: 0px; padding: 0px; text-decoration: none; color: rgb(51, 85, 170); outline: 0px; line-height: 1.5;"><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014230406054101.jpg" class="img-ks-lazyload"/></a></p><p style="margin-top: 1.12em; margin-bottom: 1.12em; white-space: normal; padding: 0px; line-height: 1.4;"><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014231610012195.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014232675092555.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014232979062088.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014235769069478.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014236780094014.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014238592040561.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014239836044707.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014242935014263.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014243816058372.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014246095080728.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014247161046424.png" class="img-ks-lazyload"/></p><p><br/></p>
     * utime : 1495698422000
     * att : []
     * state : 1
     * ctime : 1493014299000
     * shopsName : null
     * id : 14
     * stock : 6
     * isCollection : null
     * comment : null
     * shopsId : 11
     * goodsName : 【限时直降】Apple/苹果 iPhone 7 32G 全网通4G智能手机原封国行
     * goodsType : 0001
     * priceS : 4888
     */

    private String goodsInfo;
    private long utime;
    private String state;
    private long ctime;
    private Object shopsName;
    private int id;
    private int stock;
    private Object isCollection;
    private Object comment;
    private int shopsId;
    private String goodsName;
    private String goodsType;
    private int priceS;
    private List<?> att;

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(long utime) {
        this.utime = utime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public Object getShopsName() {
        return shopsName;
    }

    public void setShopsName(Object shopsName) {
        this.shopsName = shopsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Object getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Object isCollection) {
        this.isCollection = isCollection;
    }

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public int getShopsId() {
        return shopsId;
    }

    public void setShopsId(int shopsId) {
        this.shopsId = shopsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public int getPriceS() {
        return priceS;
    }

    public void setPriceS(int priceS) {
        this.priceS = priceS;
    }

    public List<?> getAtt() {
        return att;
    }

    public void setAtt(List<?> att) {
        this.att = att;
    }
}
