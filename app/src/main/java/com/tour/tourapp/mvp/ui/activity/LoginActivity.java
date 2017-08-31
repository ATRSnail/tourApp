package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.tour.tourapp.R;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.RspUserBean;
import com.tour.tourapp.utils.CheckDataIsEmpty;
import com.tour.tourapp.utils.TransformUtils;
import com.tour.tourapp.utils.UT;
import com.tour.tourapp.utils.UserManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone_login)
    EditText phoneEt;
    @BindView(R.id.code_login)
    EditText passwordEt;
    private String phoneNum;
    private String password;
    private SweetAlertDialog pDialog;

    @Inject
    Activity mActivity;

    public static void launch(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        setCustomTitle("登录");
        setRightTitle("注册");
        initDialog();
        phoneEt.setText("11111111111");
        passwordEt.setText("xuch");
        right_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              RegisterActivity.launch(mActivity);
            }
        });
    }

    private void initDialog() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
    }

    @OnClick(R.id.btnSure)
    public void onLogin(View view) {

        phoneNum = phoneEt.getText().toString().trim();
        password = passwordEt.getText().toString().trim();
        if (CheckDataIsEmpty.checkString(phoneNum) ||CheckDataIsEmpty.checkString(password)) {
            UT.show("输入不能为空");
            return;
        }
        pDialog.show();
        RetrofitManager.getInstance(1).getLoginInObservable(phoneNum, password)
                .compose(TransformUtils.<RspUserBean>defaultSchedulers())
                .subscribe(new Subscriber<RspUserBean>() {
                    @Override
                    public void onCompleted() {
                        KLog.d();
                        pDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                        pDialog.dismiss();
                    }

                    @Override
                    public void onNext(RspUserBean rspUserBean) {
                        KLog.a("user--->" + rspUserBean.toString());
                        if (rspUserBean.getHead().getRspCode().equals("0")) {
                            UserManager.cacheUserInfo(new Gson().toJson(rspUserBean.getBody().getUser()));
                            startActivity(new Intent(LoginActivity.this, MainTabActivity.class));
                            LoginActivity.this.finish();
                        } else {
                            UT.show(rspUserBean.getHead().getRspMsg());
                        }
                    }
                });

    }

}
