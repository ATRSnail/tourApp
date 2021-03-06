package com.tour.tourapp.mvp.presenter.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.RspNearbyShopBean;
import com.tour.tourapp.mvp.interactor.ShopAroundInter;
import com.tour.tourapp.mvp.interactor.impl.ShopAroundInterImpl;
import com.tour.tourapp.mvp.presenter.ShopAroundPresenter;
import com.tour.tourapp.mvp.presenter.base.BasePresenterImpl;
import com.tour.tourapp.mvp.view.base.ShopAroundView;

import javax.inject.Inject;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public class ShopAroundPresenterImpl extends BasePresenterImpl<ShopAroundView,RspNearbyShopBean> implements ShopAroundPresenter{

    private ShopAroundInter<RspNearbyShopBean> mNewsInteractor;
    private int pageNum;
    private int numPerPage;
    private String latitude, longitude;
    private boolean mIsRefresh = true;
    private boolean misFirstLoad;

    @Inject
    public ShopAroundPresenterImpl(ShopAroundInterImpl mNewsInteractor) {
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
            mView.setShopAroundList(null, loadType);
        }
    }

    @Override
    public void setParams(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public void success(RspNearbyShopBean data) {
        misFirstLoad = true;
        KLog.a("ddd----success");
        if (data == null || data.getBody() == null) return;
        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        pageNum++;
        if (mView != null) {
            mView.setShopAroundList(data.getBody().getShops(), loadType);
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
        mSubscription = mNewsInteractor.loadNews(this, latitude,longitude);
    }
}
