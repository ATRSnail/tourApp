package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/25.
 * 根据商铺id查询该商铺下所有商品
 *
 * 根据商品的id查询商品的详细信息
 *
 * 分类 商店 -商品信息的详情
 *
 *   商品详情一致---共用
 * 14个信息
 */

public class GoodsDetailBean {
    @Override
    public String toString() {
        return "GoodsDetailBean{" +
                "goodsInfo='" + goodsInfo + '\'' +
                ", utime=" + utime +
                ", state='" + state + '\'' +
                ", ctime=" + ctime +
                ", shopsName='" + shopsName + '\'' +
                ", id=" + id +
                ", stock=" + stock +
                ", isCollection=" + isCollection +
                ", shopsId=" + shopsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", priceS=" + priceS +
                ", att=" + att +
                ", comment=" + comment +
                '}';
    }

    /**
     * goodsInfo : <p style="margin-top: 1.12em; margin-bottom: 1.12em; white-space: normal; padding: 0px; line-height: 1.4;"><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014641150062611.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014641638038087.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014642481031190.jpg" class="img-ks-lazyload"/></p><p style="margin-top: 1.12em; margin-bottom: 1.12em; white-space: normal; padding: 0px; line-height: 1.4;"><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014643772045248.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014644087094011.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014645435013505.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014646782077953.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014648172002055.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014649103016992.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014652205074701.jpg" class="img-ks-lazyload"/></p><p style="margin-top: 1.12em; margin-bottom: 1.12em; white-space: normal; padding: 0px; line-height: 1.4;"><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014654331089138.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014655118059253.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014655689026706.jpg" class="img-ks-lazyload"/></p><p style="margin-top: 1.12em; margin-bottom: 1.12em; white-space: normal; padding: 0px; line-height: 1.4;"><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014656097012526.jpg" class="img-ks-lazyload"/></p><p style="white-space: normal;"><img width="790" src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014656474006950.png" alt="价格说明"/></p><p style="white-space: normal;"><span style="text-decoration: line-through;"><strong style="margin: 0px; padding: 0px;"></strong></span></p><p style="white-space: normal;"><span style="margin: 0px; padding: 0px;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; white-space: normal; padding: 0px; line-height: 1.4;"><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014656664081811.jpg" alt=" 分期购.jpg" class="img-ks-lazyload"/><img src="http://172.16.10.15:9400/ueditor/ueditor/jsp/upload/image/20170424/1493014656799076707.jpg" class="img-ks-lazyload"/></p><p style="white-space: normal;"><br/></p><p><br/></p>
     * utime : 1495594505000
     * att : [{"id":35,"utime":1495178143000,"goodsId":16,"attUrl":"/shopsGoods/767486d52a1548c6a6801ea85f488186.jpg","attType":"0001","ctime":1495178143000},{"id":36,"utime":1495178161000,"goodsId":16,"attUrl":"/shopsGoods/a154178eaba8440489a81fea9b4dc670.jpg","attType":"0002","ctime":1495178161000}]
     * state : 1
     * ctime : 1493014697000
     * shopsName : 苏宁易购官方旗舰店
     * id : 16
     * stock : 4
     * isCollection : null
     * comment : [{"phoneNo":"13566892504","content":"你脚后跟首都哈U盾很快就肯定会嫉妒个人","id":4,"goodsId":16,"orderInfoId":5,"userId":12,"ctime":1494492745000,"goodsName":"【99元定金预售限量买赠】Huawei/华为 畅享7 Plus 高配智能手机"}]
     * shopsId : 11
     * goodsName : 【99元定金预售限量买赠】Huawei/华为 畅享7 Plus 高配智能手机
     * goodsType : 0003
     * priceS : 99
     */

    private String goodsInfo;
    private long utime;
    private String state;
    private long ctime;
    private String shopsName;
    private int id;
    private int stock;
    private Object isCollection;
    private int shopsId;
    private String goodsName;
    private String goodsType;
    private double priceS;
    private List<AttBean> att;
    private List<CommentBean> comment;

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


    public double getPriceS() {
        return priceS;
    }

    public void setPriceS(double priceS) {
        this.priceS = priceS;
    }

    public List<AttBean> getAtt() {
        return att;
    }

    public void setAtt(List<AttBean> att) {
        this.att = att;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }




}
