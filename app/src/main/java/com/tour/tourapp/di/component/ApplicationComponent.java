package com.tour.tourapp.di.component;

import android.content.Context;

import com.tour.tourapp.di.module.ApplicationModule;
import com.tour.tourapp.di.scope.ContextLife;
import com.tour.tourapp.di.scope.PerApp;

import dagger.Component;

/**
 * @author xch
 * @version 1.0
 * @create_date 16/12/22
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}