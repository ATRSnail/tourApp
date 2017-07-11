package com.tour.tourapp.entity;

/**
 * Created by ATRSnail on 2017/5/23.
 *根据收货地址id  查询的收货地址
 */

public class UserAddObjBean {
    UserAddBean aPPUserAdd;

    public UserAddBean getaPPUserAdd() {
        return aPPUserAdd;
    }

    public void setaPPUserAdd(UserAddBean aPPUserAdd) {
        this.aPPUserAdd = aPPUserAdd;
    }

    @Override
    public String toString() {
        return "UserAddObjBean{" +
                "aPPUserAdd=" + aPPUserAdd +
                '}';
    }
}
