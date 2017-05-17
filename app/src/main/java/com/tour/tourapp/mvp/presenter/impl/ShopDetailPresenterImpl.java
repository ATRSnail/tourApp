package com.tour.tourapp.mvp.presenter.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.RspShopDetailBean;
import com.tour.tourapp.mvp.interactor.ShopDetailInter;
import com.tour.tourapp.mvp.interactor.impl.ShopDetailInterImpl;
import com.tour.tourapp.mvp.presenter.ShopDetailPresenter;
import com.tour.tourapp.mvp.presenter.base.BasePresenterImpl;
import com.tour.tourapp.mvp.view.base.ShopDetailView;

import javax.inject.Inject;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public class ShopDetailPresenterImpl extends BasePresenterImpl<ShopDetailView, RspShopDetailBean> implements ShopDetailPresenter {

    private ShopDetailInter<RspShopDetailBean> mNewsInteractor;
    private int pageNum;
    private int numPerPage;
    private int id;
    private boolean mIsRefresh = true;
    private boolean misFirstLoad;

    @Inject
    public ShopDetailPresenterImpl(ShopDetailInterImpl mNewsInteractor) {
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
    public void setParams(int id) {
        this.id = id;
    }

    @Override
    public void success(RspShopDetailBean data) {
        misFirstLoad = true;
        KLog.a("ddd----success");
        if (data == null || data.getBody() == null) return;
        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        pageNum++;
        if (mView != null) {
            mView.setAreaBeanList(data.getBody(), loadType);
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
        mSubscription = mNewsInteractor.loadNews(this, id);
    }
}
