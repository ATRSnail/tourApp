package com.tour.tourapp.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.tour.tourapp.R;

public class RegisterActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {

    }
}
