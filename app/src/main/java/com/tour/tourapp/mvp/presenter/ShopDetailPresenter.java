package com.tour.tourapp.mvp.presenter;

import com.tour.tourapp.mvp.presenter.base.BaseRefreshPresenter;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public interface ShopDetailPresenter extends BaseRefreshPresenter{

    void setParams(int id,int pageNum,int numPerPage,String goodsType,String attType);
}
