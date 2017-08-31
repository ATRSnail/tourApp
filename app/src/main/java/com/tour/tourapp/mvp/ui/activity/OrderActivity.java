package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tour.tourapp.R;

import javax.inject.Inject;

/**
 * 订单页面
 */
public class OrderActivity extends BaseActivity {

    @Inject
    Activity mActivity;

    public static void launch(Context context) {
        Intent intent = new Intent(context, OrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        setCustomTitle("订单填写");
    }

    public void submitOrder(View v) {
        PayActivity.launch(mActivity);
    }


}
