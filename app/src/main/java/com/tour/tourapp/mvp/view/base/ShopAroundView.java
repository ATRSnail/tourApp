package com.tour.tourapp.mvp.view.base;

import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.ShopBean;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public interface ShopAroundView extends BaseView{

    void setAreaBeanList(List<ShopBean> areaBeanList, @LoadNewsType.checker int loadType);
}
