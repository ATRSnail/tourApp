package com.tour.tourapp.mvp.interactor.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.RspSearchBean;
import com.tour.tourapp.mvp.interactor.ShopAroundInter;
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

public class ShopAroundInterImpl implements ShopAroundInter<RspSearchBean> {

    @Inject
    public ShopAroundInterImpl() {
    }

    @Override
    public Subscription loadNews(final RequestCallBack<RspSearchBean> listener, String latitude, String longitude) {

        RetrofitManager.getInstance(1).getShopsListObservable(latitude, longitude)
                .compose(TransformUtils.<RspSearchBean>defaultSchedulers())
                .subscribe(new Subscriber<RspSearchBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RspSearchBean rspSearchBean) {

                        KLog.d("shop--->" + rspSearchBean.toString());
                        listener.success(rspSearchBean);
                    }
                });
        return null;
    }
}
