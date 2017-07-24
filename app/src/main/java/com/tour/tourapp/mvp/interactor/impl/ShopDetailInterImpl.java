package com.tour.tourapp.mvp.interactor.impl;

import com.socks.library.KLog;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.RspShopAllGoodBean;
import com.tour.tourapp.mvp.interactor.ShopDetailInter;
import com.tour.tourapp.mvp.listener.RequestCallBack;
import com.tour.tourapp.utils.TransformUtils;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * 商铺 --- 所有商品
 */

public class ShopDetailInterImpl implements ShopDetailInter<RspShopAllGoodBean> {

    @Inject
    public ShopDetailInterImpl() {
    }

    @Override
    public Subscription loadNews(final RequestCallBack<RspShopAllGoodBean> listener, String id,String goodsType,String attType,String page,String size) {

   return      RetrofitManager.getInstance(1).getGoodsByShopId(id,goodsType,attType,page,size)
                .compose(TransformUtils.<RspShopAllGoodBean>defaultSchedulers())
                .subscribe(new Subscriber<RspShopAllGoodBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                        listener.onError(e.toString());
                    }

                    @Override
                    public void onNext(RspShopAllGoodBean rspShopBean) {

                        KLog.d(rspShopBean.toString());
                        listener.success(rspShopBean);
                    }
                });
    }
}
