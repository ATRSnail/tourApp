package com.tour.tourapp.di.module;

import android.content.Context;

import com.tour.tourapp.App;
import com.tour.tourapp.di.scope.ContextLife;
import com.tour.tourapp.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * @author xch
 * @version 1.0
 * @create_date 16/12/22
 */
@Module
public class ApplicationModule {
    private App mApplication;

    public ApplicationModule(App application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

}