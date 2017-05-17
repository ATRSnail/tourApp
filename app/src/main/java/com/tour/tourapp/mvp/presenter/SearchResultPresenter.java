package com.tour.tourapp.mvp.presenter;

import com.tour.tourapp.mvp.presenter.base.BaseRefreshPresenter;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public interface SearchResultPresenter extends BaseRefreshPresenter{

    void setParams(String shopsName,String goodsName);

}
