package com.tour.tourapp.mvp.interactor;

import com.tour.tourapp.mvp.listener.RequestCallBack;

import rx.Subscription;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public interface ShopAroundInter<T> {

    Subscription loadNews(RequestCallBack<T> listener,String latitude,String longitude);
}
