package com.tour.tourapp.mvp.interactor.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.RspGoodDetailBean;
import com.tour.tourapp.entity.RspShopDetail;
import com.tour.tourapp.mvp.interactor.GoodInfoInter;
import com.tour.tourapp.mvp.listener.RequestCallBack;
import com.tour.tourapp.utils.TransformUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by ATRSnail on 2017/7/21.
 * 商铺---信息
 */

public class GoodInfoIntermpl implements GoodInfoInter<RspGoodDetailBean> {

    @Inject
    public GoodInfoIntermpl() {
    }


    @Override
    public Subscription loadNews(final RequestCallBack<RspGoodDetailBean> listener, String id) {
        return RetrofitManager.getInstance(1).goodsDetail(id)
                .compose(TransformUtils.<RspGoodDetailBean>defaultSchedulers())
                .subscribe(new Subscriber<RspGoodDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(RspGoodDetailBean rspGoodDetailBean) {
                        KLog.d(rspGoodDetailBean.toString());
                        listener.success(rspGoodDetailBean);
                    }
                });
    }
}
