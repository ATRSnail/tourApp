package com.tour.tourapp.entity;

/**
 * 用户 信息
 */
public class UserObjBean {

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserObjBean{" +
                "user=" + user +
                '}';
    }
}
