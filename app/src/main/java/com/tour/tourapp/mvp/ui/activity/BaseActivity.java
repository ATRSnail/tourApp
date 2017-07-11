package com.tour.tourapp.mvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socks.library.KLog;
import com.tour.tourapp.App;
import com.tour.tourapp.R;
import com.tour.tourapp.di.component.ActivityComponent;
import com.tour.tourapp.di.component.DaggerActivityComponent;
import com.tour.tourapp.di.module.ActivityModule;
import com.tour.tourapp.mvp.presenter.base.BasePresenter;
import com.tour.tourapp.utils.NetUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * @author xch
 * @version 1.0
 * @create_date 16/12/22
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    @BindView(R.id.toolbar_top)
    public Toolbar mToolbar;

    @BindView(R.id.middle_top)
    TextView mToolbarTitle;
    @BindView(R.id.right_top)
    TextView right_top;

    protected ActivityComponent mActivityComponent;

    protected T mPresenter;

    protected Subscription mSubscription;

    protected Bundle savedInstanceState;

    public abstract int getLayoutId();

    public abstract void initInjector();

    public abstract void initViews();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i(getClass().getSimpleName());
        this.savedInstanceState = savedInstanceState;
        NetUtil.isNetworkErrThenShowMsg();
        initActivityComponent();
        int layoutId = getLayoutId();
        setContentView(layoutId);
        initInjector();
        ButterKnife.bind(this);

        if (mPresenter != null){
            mPresenter.onCreate();
        }

        if (mToolbar!=null){
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            mToolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        initViews();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null){
            mPresenter.onDestory();
        }
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    public void setCustomTitle(String title) {
        if (mToolbarTitle != null)
            mToolbarTitle.setText(title);
    }

    public void hideToolBar() {
        if (mToolbar != null)
            mToolbar.setVisibility(View.GONE);
    }

    public void hideRight() {
        if (right_top != null)
            mToolbar.setVisibility(View.GONE);
    }


}
