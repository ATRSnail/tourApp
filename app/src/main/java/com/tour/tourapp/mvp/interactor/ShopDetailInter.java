package com.tour.tourapp.mvp.interactor;

import com.tour.tourapp.mvp.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public interface ShopDetailInter<T> {

    Subscription loadNews(RequestCallBack<T> listener,String id ,String goodsType,String attType,String page,String size);
}
