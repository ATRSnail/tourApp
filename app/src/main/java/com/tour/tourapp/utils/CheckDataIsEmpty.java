package com.tour.tourapp.utils;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by ATRSnail on 2017/7/17.
 * 检查数据是否 为空
 */

public class CheckDataIsEmpty {

    public static boolean checkList(List list) {
        if (list != null && list.size() > 0)
            return false;
        return true;
    }

    public static boolean checkString(String string) {
        if ("".equals(string) && !TextUtils.isEmpty(string))
            return false;
        return true;
    }

}
