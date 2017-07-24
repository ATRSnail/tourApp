package com.tour.tourapp.mvp.interactor;

import com.tour.tourapp.mvp.listener.RequestCallBack;

import rx.Subscription;

/**
 *
 */

public interface GoodInfoInter<T> {

    Subscription loadNews(RequestCallBack<T> listener, String id);

}
