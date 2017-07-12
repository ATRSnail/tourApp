package com.tour.tourapp.mvp.presenter.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.RspShopAllGoodBean;
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

public class ShopDetailPresenterImpl extends BasePresenterImpl<ShopDetailView, RspShopAllGoodBean> implements ShopDetailPresenter {

    private ShopDetailInter<RspShopAllGoodBean> mNewsInteractor;
    private int pageNum;
    private int numPerPage;
    private int id;
    String goodsType;
    String attType;
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
    public void setParams(int id,int pageNum,int numPerPage,String goodsType,String attType) {
        this.id = id;
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
        this.goodsType = goodsType;
        this.attType = attType;
    }

    @Override
    public void success(RspShopAllGoodBean data) {
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
        mSubscription = mNewsInteractor.loadNews(this,String.valueOf(id),goodsType,attType,String.valueOf(pageNum),String.valueOf(numPerPage));
    }
}
