package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.tour.tourapp.R;
import com.tour.tourapp.utils.UT;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 支付页面
 */
public class PayActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, PayActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.zfb_pay)
    CheckBox zfb_pay;
    @BindView(R.id.wx_pay)
    CheckBox wx_pay;

    @Inject
    Activity mActivity;


    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        setCustomTitle("支付页面");
    }


    public void affirmPay(View v) {
        if (zfb_pay.isChecked() && wx_pay.isChecked()) {
            UT.show("请选择一种支付方式");
           

            return;
        }
        if (!zfb_pay.isChecked() && !wx_pay.isChecked()) {
            UT.show("请选择一种支付方式");
            return;
        }
        if (zfb_pay.isChecked()) {
            UT.show("支付宝");
        } else if (wx_pay.isChecked()) {
            UT.show("微信");
        }

    }

}
