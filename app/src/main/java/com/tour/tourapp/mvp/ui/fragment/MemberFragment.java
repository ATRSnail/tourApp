package com.tour.tourapp.mvp.ui.fragment;

import android.view.View;

import com.tour.tourapp.R;
import com.tour.tourapp.mvp.ui.activity.LoginActivity;
import com.tour.tourapp.mvp.ui.activity.RegisterActivity;

import butterknife.OnClick;

public class MemberFragment extends BaseLazyFragment {

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {

    }

    @OnClick({R.id.tv_login, R.id.tv_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                LoginActivity.launch(getContext());
                break;
            case R.id.tv_register:
                RegisterActivity.launch(getContext());
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_member;
    }

    @Override
    public void onFirstUserVisible() {

    }
}
