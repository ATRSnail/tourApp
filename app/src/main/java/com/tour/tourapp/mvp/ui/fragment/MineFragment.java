package com.tour.tourapp.mvp.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.tour.tourapp.R;
import com.tour.tourapp.entity.UserBean;
import com.tour.tourapp.mvp.ui.activity.LoginActivity;
import com.tour.tourapp.mvp.ui.activity.RegisterActivity;
import com.tour.tourapp.utils.UserManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人中心
 */
public class MineFragment extends BaseLazyFragment {

    @BindView(R.id.login_mine)
    TextView login_mine;
    @BindView(R.id.register_mine)
    TextView register_mine;
    @BindView(R.id.name_mine)
    TextView name_mine;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
            if (UserManager.isLogin()){
                name_mine.setVisibility(View.VISIBLE);
                login_mine.setVisibility(View.GONE);
                register_mine.setVisibility(View.GONE);
            }else {
                name_mine.setVisibility(View.GONE);
                login_mine.setVisibility(View.VISIBLE);
                register_mine.setVisibility(View.VISIBLE);
            }
    }

    @OnClick({R.id.login_mine, R.id.register_mine})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_mine:
                LoginActivity.launch(getContext());
                break;
            case R.id.register_mine:
                RegisterActivity.launch(getContext());
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onFirstUserVisible() {

    }
}
