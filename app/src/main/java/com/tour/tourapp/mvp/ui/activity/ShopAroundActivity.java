package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dl7.recycler.divider.RecyclerViewDivider;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.socks.library.KLog;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.ShopDetailBean;
import com.tour.tourapp.mvp.adapter.ShopAroundAdapter;
import com.tour.tourapp.mvp.presenter.impl.ShopAroundPresenterImpl;
import com.tour.tourapp.mvp.view.base.ShopAroundView;
import com.tour.tourapp.utils.CheckDataIsEmpty;
import com.tour.tourapp.utils.InitUtils;
import com.tour.tourapp.utils.NetUtil;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 周边店铺
 */
public class ShopAroundActivity extends BaseActivity implements OnRecyclerViewItemClickListener
        , SwipeRefreshLayout.OnRefreshListener, ShopAroundView {

    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";
    @BindView(R.id.sr_sa)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.rv_sa)
    RecyclerView recyclerView;
    private ShopAroundAdapter shopAroundAdapter;
    private List<ShopDetailBean> shopBeen;
    private double latitude, longitude;

    @Inject
    Activity mActivity;
    @Inject
    ShopAroundPresenterImpl mShopAroundPreter;

    public static void launch(Context context, double latitude, double longitude) {
        Intent intent = new Intent(context, ShopAroundActivity.class);
        intent.putExtra(LATITUDE_KEY, latitude);
        intent.putExtra(LONGITUDE_KEY, longitude);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_around;
    }

    @Override
    public void initInjector() {
        latitude = getIntent().getDoubleExtra(LATITUDE_KEY, 0);
        longitude = getIntent().getDoubleExtra(LONGITUDE_KEY, 0);
//        latitude = 39.993521;
//        longitude = 116.377415;
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        setCustomTitle("周边商铺");
        InitUtils.initSwipRefresh(swipe_refresh,this);
        initRecyclerView();
        initData();

    }



    /**
     * 初始化recycleView
     */
    private void initRecyclerView() {
        shopBeen = new ArrayList<>();
        shopAroundAdapter = new ShopAroundAdapter(this, shopBeen);
        shopAroundAdapter.setOnItemClickListener(this);
        RecyclerViewHelper.initRecyclerViewV(mActivity, recyclerView,
                true, shopAroundAdapter, 20, Color.RED);

    }

    private void initData() {
        mShopAroundPreter.setParams(latitude + "", longitude + "");
        mShopAroundPreter.attachView(this);
        mPresenter = mShopAroundPreter;
        mPresenter.onCreate();
    }

    @Override
    public void onItemClick(View view, int position) {
        if (!CheckDataIsEmpty.checkList(shopBeen)){
            ShopDetailActivity.launch(ShopAroundActivity.this, shopBeen.get(position).getId());
        }

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
            UT.showNormal(msg);
        }
    }

    @Override
    public void setShopAroundList(List<ShopDetailBean> shops, @LoadNewsType.checker int loadType) {
        switch (loadType) {
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                shopBeen = shops;
                swipe_refresh.setRefreshing(false);
                shopAroundAdapter.updateItems(shops);
                break;
            case LoadNewsType.TYPE_REFRESH_ERROR:
                swipe_refresh.setRefreshing(false);
                break;
            case LoadNewsType.TYPE_LOAD_MORE_SUCCESS:


                break;
            case LoadNewsType.TYPE_LOAD_MORE_ERROR:

                break;
        }
    }

    @Override
    public void onRefresh() {
        mShopAroundPreter.refreshData();
    }
}
