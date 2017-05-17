package com.tour.tourapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.socks.library.KLog;
import com.tour.tourapp.di.component.ApplicationComponent;
import com.tour.tourapp.di.component.DaggerApplicationComponent;
import com.tour.tourapp.di.module.ApplicationModule;

/**
 * Created by long on 2016/8/19.
 * Application
 */
public class App extends Application{

    private static Context sAppContext;
    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        sAppContext = this;
        initActivityLifecycleLogs();
        initApplicationComponent();
        KLog.init(BuildConfig.LOG_DEBUG);
    }

    private void initActivityLifecycleLogs() {
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                KLog.v("=========", activity + "  onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                KLog.v("=========", activity + "  onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                KLog.v("=========", activity + "  onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                KLog.v("=========", activity + "  onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                KLog.v("=========", activity + "  onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                KLog.v("=========", activity + "  onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                KLog.v("=========", activity + "  onActivityDestroyed");
            }
        });
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static Context getAppContext() {
        return sAppContext;
    }

}
