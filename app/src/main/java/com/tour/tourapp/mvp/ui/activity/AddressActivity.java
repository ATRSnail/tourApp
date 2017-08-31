package com.tour.tourapp.mvp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tour.tourapp.R;

/**
 * 我的地址
 */
public class AddressActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void initInjector() {
          mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {

    }


}
