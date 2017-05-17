package com.tour.tourapp.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.socks.library.KLog;
import com.tour.tourapp.R;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.RspUserBean;
import com.tour.tourapp.utils.TransformUtils;
import com.tour.tourapp.utils.UT;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;

/**
 * @author xch
 * @version 1.0
 * @create_date 16/12/27
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phoneEt;
    @BindView(R.id.code)
    EditText passwordEt;
    private String phoneNum;
    private String password;
    private SweetAlertDialog pDialog;

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
        initDialog();
        phoneEt.setText("11111111111");
        passwordEt.setText("xuch");
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
        if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(password)) {
            UT.show("不能为空");
            return;
        }
        pDialog.show();
        KLog.d("ddddd");
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
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
                        } else {
                            UT.show(rspUserBean.getHead().getRspMsg());
                        }
                    }
                });

    }

}
