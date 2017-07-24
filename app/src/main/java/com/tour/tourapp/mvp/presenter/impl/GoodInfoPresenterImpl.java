package com.tour.tourapp.mvp.presenter.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.RspGoodDetailBean;
import com.tour.tourapp.mvp.interactor.GoodInfoInter;
import com.tour.tourapp.mvp.interactor.impl.GoodInfoIntermpl;
import com.tour.tourapp.mvp.presenter.GoodInfoPresenter;
import com.tour.tourapp.mvp.presenter.base.BasePresenterImpl;
import com.tour.tourapp.mvp.view.base.GoodInfoView;

import javax.inject.Inject;


/**
 * Created by ATRSnail on 2017/7/21.
 */

public class GoodInfoPresenterImpl extends BasePresenterImpl<GoodInfoView, RspGoodDetailBean> implements GoodInfoPresenter {

    private GoodInfoInter<RspGoodDetailBean> goodInfoInter;
    private int pageNum;
    private int numPerPage;
    private int id;
    String goodsType;
    String attType;


    private boolean mIsRefresh = true;
    private boolean misFirstLoad;

    @Inject
    public GoodInfoPresenterImpl(GoodInfoIntermpl mNewsInteractor) {
        this.goodInfoInter = mNewsInteractor;
    }


    @Override
    public void onCreate() {
        if (mView != null) {
            loadData();
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
            mView.setGoodInfo(null, loadType);
        }
    }



    @Override
    public void success(RspGoodDetailBean data) {
        super.success(data);
        misFirstLoad = true;
        KLog.a("ddd----success");
        if (data == null || data.getBody() == null) return;
        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        pageNum++;
        if (mView != null) {
            mView.setGoodInfo(data.getBody(), loadType);
            mView.hideProgress();
        }
    }

    private void loadData() {
        mSubscription = goodInfoInter.loadNews(this, id + "");
    }

    @Override
    public void refreshData() {
        pageNum = 1;
        mIsRefresh = true;
        loadData();
    }

    @Override
    public void loadMore() {
        mIsRefresh = false;
        loadData();
    }

    @Override
    public void setParams(int id) {
        this.id = id;
    }
}
