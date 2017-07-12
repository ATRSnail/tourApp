package com.tour.tourapp.mvp.view.base;

import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodsDetailBean;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public interface GoodListView extends BaseView{

    void setAreaBeanList(List<GoodsDetailBean> areaBeanList, @LoadNewsType.checker int loadType);
}
