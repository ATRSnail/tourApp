package com.tour.tourapp.mvp.presenter.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.RspNearbyShopBean;
import com.tour.tourapp.mvp.interactor.SearchResultInter;
import com.tour.tourapp.mvp.interactor.impl.SearchResultInterImpl;
import com.tour.tourapp.mvp.presenter.SearchResultPresenter;
import com.tour.tourapp.mvp.presenter.base.BasePresenterImpl;
import com.tour.tourapp.mvp.view.base.ShopAroundView;

import javax.inject.Inject;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public class SearchResultPresenterImpl extends BasePresenterImpl<ShopAroundView, RspNearbyShopBean> implements SearchResultPresenter {

    private SearchResultInter<RspNearbyShopBean> mNewsInteractor;
    private int pageNum;
    private int numPerPage;
    private boolean mIsRefresh = true;
    private boolean misFirstLoad;
    private String shopsName, goodsName;

    @Inject
    public SearchResultPresenterImpl(SearchResultInterImpl mNewsInteractor) {
        this.mNewsInteractor = mNewsInteractor;
    }

    @Override
    public void onCreate() {
        if (mView != null) {
            loadNewsData();
        }
    }

    @Override
    public void beforeRequest() {
        if (!misFirstLoad) {
            mView.showProgress();
        }
    }

    @Override
    public void onError(String errorMsg) {
        super.onError(errorMsg);
        if (mView != null) {
            int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_ERROR : LoadNewsType.TYPE_LOAD_MORE_ERROR;
            mView.setAreaBeanList(null, loadType);
        }
    }

    @Override
    public void setParams(String shopsName, String goodsName) {
        this.shopsName = shopsName;
        this.goodsName = goodsName;
    }

    @Override
    public void success(RspNearbyShopBean data) {
        misFirstLoad = true;
        KLog.a("ddd----success");
        if (data == null || data.getBody() == null) return;
        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        pageNum++;
        if (mView != null) {
            mView.setAreaBeanList(data.getBody().getShops(), loadType);
            mView.hideProgress();
        }
    }

    @Override
    public void refreshData() {
        pageNum = 1;
        mIsRefresh = true;
        loadNewsData();
    }

    @Override
    public void loadMore() {
        mIsRefresh = false;
        loadNewsData();
    }

    private void loadNewsData() {
        mSubscription = mNewsInteractor.loadNews(this, shopsName, goodsName);
    }
}
