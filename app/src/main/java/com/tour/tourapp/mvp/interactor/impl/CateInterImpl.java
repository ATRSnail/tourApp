package com.tour.tourapp.mvp.interactor.impl;

import android.util.Log;

import com.socks.library.KLog;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.Rspclassify;
import com.tour.tourapp.mvp.interactor.CateInter;
import com.tour.tourapp.mvp.interactor.GoodListInter;
import com.tour.tourapp.mvp.listener.RequestCallBack;
import com.tour.tourapp.utils.MyUtils;
import com.tour.tourapp.utils.TransformUtils;
import com.tour.tourapp.utils.UT;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * 分类--首页（请求数据）
 */

public class CateInterImpl implements CateInter<Rspclassify> {

    @Inject
    public CateInterImpl() {
    }

    @Override
    public Subscription loadNews(final RequestCallBack<Rspclassify> listener) {

        return RetrofitManager.getInstance(1).classify_First()
                .compose(TransformUtils.<Rspclassify>defaultSchedulers())
                .subscribe(new Subscriber<Rspclassify>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                        listener.onError(MyUtils.analyzeNetworkError(e));
                    }

                    @Override
                    public void onNext(Rspclassify rspClassify) {
                        listener.success(rspClassify);
                    }
                });

    }
}
