package com.tour.tourapp.mvp.interactor.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.RspNearbyShopBean;
import com.tour.tourapp.mvp.interactor.SearchResultInter;
import com.tour.tourapp.mvp.listener.RequestCallBack;
import com.tour.tourapp.utils.MyUtils;
import com.tour.tourapp.utils.TransformUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public class SearchResultInterImpl implements SearchResultInter<RspNearbyShopBean> {

    @Inject
    public SearchResultInterImpl() {
    }

    @Override
    public Subscription loadNews(final RequestCallBack<RspNearbyShopBean> listener, String shopsName, String goodsName) {

        return  RetrofitManager.getInstance(1).searchGoodsOrShop(shopsName,goodsName)
                .compose(TransformUtils.<RspNearbyShopBean>defaultSchedulers())
                .subscribe(new Subscriber<RspNearbyShopBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                        listener.onError(MyUtils.analyzeNetworkError(e));
                    }

                    @Override
                    public void onNext(RspNearbyShopBean rspNearbyShopBean) {

                        KLog.d(rspNearbyShopBean.toString());
                        listener.success(rspNearbyShopBean);
                    }
                });

    }
}
