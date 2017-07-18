package com.tour.tourapp.di.component;

import android.app.Activity;
import android.content.Context;

import com.tour.tourapp.di.module.ActivityModule;
import com.tour.tourapp.di.scope.ContextLife;
import com.tour.tourapp.di.scope.PerActivity;
import com.tour.tourapp.mvp.ui.activity.SearchActivity;
import com.tour.tourapp.mvp.ui.activity.SearchResultActivity;
import com.tour.tourapp.mvp.ui.activity.ShopAroundActivity;
import com.tour.tourapp.mvp.ui.activity.ShopDetailActivity;

import dagger.Component;

/**
 * @author xch
 * @version 1.0
 * @create_date 16/12/24
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(ShopAroundActivity shopAroundActivity);


    void inject(ShopDetailActivity shopDetailActivity);

    void inject(SearchResultActivity searchResultActivity);

    void inject(SearchActivity searchActivity);
}
