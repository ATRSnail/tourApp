package com.tour.tourapp.mvp.view.base;

import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodDetailBeans;
import com.tour.tourapp.entity.ShopDetailBeans;

/**
 * Created by ATRSnail on 2017/7/21.
 */

public interface GoodInfoView extends BaseView{

     public void setGoodInfo(GoodDetailBeans goodDetailBeans, @LoadNewsType.checker int loadType);
}
