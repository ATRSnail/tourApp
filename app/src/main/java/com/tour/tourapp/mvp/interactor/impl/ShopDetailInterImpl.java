package com.tour.tourapp.mvp.interactor.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.RspShopDetailBean;
import com.tour.tourapp.mvp.interactor.ShopDetailInter;
import com.tour.tourapp.mvp.listener.RequestCallBack;
import com.tour.tourapp.utils.TransformUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public class ShopDetailInterImpl implements ShopDetailInter<RspShopDetailBean> {

    @Inject
    public ShopDetailInterImpl() {
    }

    @Override
    public Subscription loadNews(final RequestCallBack<RspShopDetailBean> listener,int id) {

        RetrofitManager.getInstance(1).getGoodsByShopId(id+"")
                .compose(TransformUtils.<RspShopDetailBean>defaultSchedulers())
                .subscribe(new Subscriber<RspShopDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RspShopDetailBean rspShopBean) {

                        KLog.d("shop--->" + rspShopBean.toString());
                        listener.success(rspShopBean);
                    }
                });
        return null;
    }
}
