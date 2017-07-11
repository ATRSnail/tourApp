package com.tour.tourapp.mvp.presenter.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.Rspclassify;
import com.tour.tourapp.mvp.interactor.CateInter;
import com.tour.tourapp.mvp.interactor.impl.CateInterImpl;
import com.tour.tourapp.mvp.presenter.base.BasePresenterImpl;
import com.tour.tourapp.mvp.presenter.base.BaseRefreshPresenter;
import com.tour.tourapp.mvp.view.base.CateView;

import javax.inject.Inject;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public class CatePresenterImpl extends BasePresenterImpl<CateView,Rspclassify> implements BaseRefreshPresenter {

    private CateInter<Rspclassify> mNewsInteractor;
    private int pageNum;
    private int numPerPage;

    private boolean mIsRefresh = true;
    private boolean misFirstLoad;

    @Inject
    public CatePresenterImpl(CateInterImpl mNewsInteractor) {
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
            mView.setGoodsClassifyList(null, loadType);
            mView.setShopGoodList(null, loadType);
        }
    }



    @Override
    public void success(Rspclassify data) {
        misFirstLoad = true;
        if (data == null || data.getBody() == null) return;
        KLog.a("ddd----success");
        int loadType = mIsRefresh ? LoadNewsType.TYPE_REFRESH_SUCCESS : LoadNewsType.TYPE_LOAD_MORE_SUCCESS;
        pageNum++;
        if (mView != null) {
            mView.setGoodsClassifyList(data.getBody().getGoodsClassify(), loadType);
            mView.setShopGoodList(data.getBody().getShopGoods(), loadType);
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
        mSubscription = mNewsInteractor.loadNews(this);
    }
}
