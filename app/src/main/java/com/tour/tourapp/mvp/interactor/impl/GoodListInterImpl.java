package com.tour.tourapp.mvp.interactor.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.RspGoodsBean;
import com.tour.tourapp.entity.Rspclassify;
import com.tour.tourapp.mvp.interactor.GoodListInter;
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

public class GoodListInterImpl implements GoodListInter<RspGoodsBean> {

    @Inject
    public GoodListInterImpl() {
    }

    @Override
    public Subscription loadNews(final RequestCallBack<RspGoodsBean> listener, int id) {

        return   RetrofitManager.getInstance(1).getshopGoodByIdObservable(id + "")
                .compose(TransformUtils.<RspGoodsBean>defaultSchedulers())
                .subscribe(new Subscriber<RspGoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                        listener.onError(MyUtils.analyzeNetworkError(e));
                    }

                    @Override
                    public void onNext(RspGoodsBean rspShopBean) {
                        KLog.d(rspShopBean.toString());
                        listener.success(rspShopBean);
                    }
                });

    }
}
