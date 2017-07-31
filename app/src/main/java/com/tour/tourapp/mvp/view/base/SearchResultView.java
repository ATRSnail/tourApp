package com.tour.tourapp.mvp.view.base;

import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.SearchBeans;
import com.tour.tourapp.entity.ShopDetailBean;

import java.util.List;

/**
 *
 */

public interface SearchResultView extends BaseView{

    void setShopOrGoodList(SearchBeans searchBeans, @LoadNewsType.checker int loadType);
}
