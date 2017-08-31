package com.tour.tourapp.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tour.tourapp.App;
import com.tour.tourapp.entity.UserBean;


/**
 * 用户信息管理
 */
public class UserManager {

    public static final String USER_INFO = "userinfo";
    private static Context context = App.getAppContext();

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUseInfo() {
        UserBean model = null;
        String string = SharedPrefsUtils.getStringPreference(context, USER_INFO);
        if (!TextUtils.isEmpty(string)) {
            model = new Gson().fromJson(string, UserBean.class);
        }
        return model;
    }

    /**
     * 获取用户Id
     *
     * @return
     */
    public static String getUseId() {
        UserBean model = getUseInfo();
        return model != null ? String.valueOf(model.getId()) : "";
    }

    /**
     * 获取用户电话
     *
     * @return
     */
    public static String getUsePhone() {
        UserBean model = getUseInfo();
        return model != null ? model.getPhoneNo() : "";
    }

    /**
     * 缓存用户信息
     *
     * @param json
     */
    public static void cacheUserInfo(String json) {
        SharedPrefsUtils.setStringPreference(context, USER_INFO, json);
    }

    /**
     * 清除缓存数据
     */
    public static void clearUserInfo() {
        cacheUserInfo("");
    }

    /**
     * 判断是否已登录
     *
     * @return
     */
    public static boolean isLogin() {
        UserBean model = getUseInfo();
        if (model != null) {
            return true;
        } else {
            return false;
        }
    }
}
