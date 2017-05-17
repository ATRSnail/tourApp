package com.tour.tourapp.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.tour.tourapp.R;

public class GoodDetailActivity extends BaseActivity {

    private static final String GOOD_ID = "good_id";
    public static void launch(Context context, int goodId) {
        Intent intent = new Intent(context, GoodDetailActivity.class);
        intent.putExtra(GOOD_ID, goodId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_good_detail;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {

    }

}
