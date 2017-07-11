package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/23.
 * 根据app用户的id查询所有的收货地址
 */

public class UserAddBeans {
   List<UserAddBean> APPUserAdds;

    public List<UserAddBean> getAPPUserAdds() {
        return APPUserAdds;
    }

    public void setAPPUserAdds(List<UserAddBean> APPUserAdds) {
        this.APPUserAdds = APPUserAdds;
    }

    @Override
    public String toString() {
        return "UserAddBeans{" +
                "APPUserAdds=" + APPUserAdds +
                '}';
    }
}
