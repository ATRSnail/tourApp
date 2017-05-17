package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.socks.library.KLog;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.ShopBean;
import com.tour.tourapp.mvp.adapter.ShopAroundAdapter;
import com.tour.tourapp.mvp.presenter.impl.ShopAroundPresenterImpl;
import com.tour.tourapp.mvp.view.base.ShopAroundView;
import com.tour.tourapp.utils.NetUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 周边店铺
 */
public class ShopAroundActivity extends BaseActivity implements OnRecyclerViewItemClickListener
,ShopAroundView{

    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";
    @BindView(R.id.news_rv)
    RecyclerView news_rv;
    private ShopAroundAdapter shopAroundAdapter;
    private List<ShopBean> shopBeen;
    private double latitude, longitude;

    @Inject
    Activity mActivity;
    @Inject
    ShopAroundPresenterImpl mShopAroundPreter;

    public static void launch(Context context, double latitude, double longitude) {
        Intent intent = new Intent(context, ShopAroundActivity.class);
        intent.putExtra(LATITUDE_KEY,latitude);
        intent.putExtra(LONGITUDE_KEY,longitude);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_detail;
    }

    @Override
    public void initInjector() {
        latitude = getIntent().getDoubleExtra(LATITUDE_KEY,0);
        longitude = getIntent().getDoubleExtra(LONGITUDE_KEY,0);
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        initRecyclerView();
        initData();

    }

    /**
     * 初始化recycleView
     */
    private void initRecyclerView() {
        shopBeen = new ArrayList<>();
        shopAroundAdapter = new ShopAroundAdapter(this, shopBeen);
        RecyclerViewHelper.initRecyclerViewV(this, news_rv, true, shopAroundAdapter);
        shopAroundAdapter.setOnItemClickListener(this);
        news_rv.setAdapter(shopAroundAdapter);
    }

    private void initData() {
        KLog.d("shop--->" + latitude);
        mShopAroundPreter.setParams(latitude+"",longitude+"");
        mShopAroundPreter.attachView(this);
        mPresenter = mShopAroundPreter;
        mPresenter.onCreate();
    }

    @Override
    public void onItemClick(View view, int position) {
      ShopDetailActivity.launch(ShopAroundActivity.this,9);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String msg) {
        // 网络不可用状态在此之前已经显示了提示信息
        if (NetUtil.isNetworkAvailable(mActivity)) {
            Snackbar.make(news_rv, msg, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void setAreaBeanList(List<ShopBean> areaBeanList, @LoadNewsType.checker int loadType) {
        shopAroundAdapter.updateItems(areaBeanList);
    }
}
