package com.tour.tourapp.entity;

/**
 * Created by ATRSnail on 2017/7/12.
 * 商品详情 的 comment字段
 */

public class CommentBean {
    /**
     * phoneNo : 13566892504
     * content : 你脚后跟首都哈U盾很快就肯定会嫉妒个人
     * id : 4
     * goodsId : 16
     * orderInfoId : 5
     * userId : 12
     * ctime : 1494492745000
     * goodsName : 【99元定金预售限量买赠】Huawei/华为 畅享7 Plus 高配智能手机
     */

    private String phoneNo;
    private String content;
    private int id;
    private int goodsId;
    private int orderInfoId;
    private int userId;
    private long ctime;
    private String goodsName;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(int orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
