package com.tour.tourapp.entity;

import java.io.Serializable;

/**
 * 用户 信息详情
 */
public class UserBean implements Serializable{


    /**
     * phoneNo : 11111111111
     * id : 10
     * email : null
     * nickName : null
     * state : 1
     * uTime : 1492393562000
     * cTime : 1492393562000
     */

    private String phoneNo;
    private int id;
    private Object email;
    private Object nickName;
    private String state;
    private long uTime;
    private long cTime;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getNickName() {
        return nickName;
    }

    public void setNickName(Object nickName) {
        this.nickName = nickName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getUTime() {
        return uTime;
    }

    public void setUTime(long uTime) {
        this.uTime = uTime;
    }

    public long getCTime() {
        return cTime;
    }

    public void setCTime(long cTime) {
        this.cTime = cTime;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "phoneNo='" + phoneNo + '\'' +
                ", id=" + id +
                ", email=" + email +
                ", nickName=" + nickName +
                ", state='" + state + '\'' +
                ", uTime=" + uTime +
                ", cTime=" + cTime +
                '}';
    }
}
