package com.tour.tourapp.di.component;

import android.app.Activity;
import android.content.Context;

import com.tour.tourapp.di.module.FragmentModule;
import com.tour.tourapp.di.scope.ContextLife;
import com.tour.tourapp.di.scope.PerFragment;
import com.tour.tourapp.mvp.ui.fragment.CateFragmentFragment;
import com.tour.tourapp.mvp.ui.fragment.MainFragment;

import dagger.Component;

/**
 * @author xch
 * @version 1.0
 * @create_date 16/12/22
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(MainFragment mainFragment);

    void inject(CateFragmentFragment cateFragmentFragment);
}