package com.tour.tourapp.mvp.view.base;

import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.ShopDetailBean;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public interface ShopDetailView extends BaseView{

    void setAreaBeanList(ShopDetailBean shopDetailBean, @LoadNewsType.checker int loadType);
}
