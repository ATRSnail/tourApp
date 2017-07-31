package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dl7.recycler.divider.RecyclerViewDivider;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodDetailBeans;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.entity.SearchBeans;
import com.tour.tourapp.entity.ShopBean;
import com.tour.tourapp.entity.ShopDetailBean;
import com.tour.tourapp.mvp.adapter.ShopAroundAdapter;
import com.tour.tourapp.mvp.adapter.ShopGoodAdapter2;
import com.tour.tourapp.mvp.presenter.impl.SearchResultPresenterImpl;
import com.tour.tourapp.mvp.view.base.GoodInfoView;
import com.tour.tourapp.mvp.view.base.SearchResultView;
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
 * 搜索结果 页面
 */
public class SearchResultActivity extends BaseActivity implements OnRecyclerViewItemClickListener, SearchResultView, SwipeRefreshLayout.OnRefreshListener {

    private static final String SHOP_NAME = "shop_name";
    private static final String GOOD_NAME = "good_name";

    @BindView(R.id.sr_sr)
    SwipeRefreshLayout sr_sr;
    @BindView(R.id.rv_sr)
    RecyclerView rv_sr;
    @BindView(R.id.et_search_content)
    TextView et_search_content;
    @Inject
    Activity mActivity;
    @Inject
    SearchResultPresenterImpl mSearchResultPreter;

    //true shopsName ,false goodsName
    boolean flag = true;

    private ShopAroundAdapter shopAroundAdapter;
    private ShopGoodAdapter2 shopGoodAdapter;
    private List<ShopDetailBean> shopList;
    private List<GoodsDetailBean> goodsList;
    private String shopsName, goodsName;

   static SearchActivity searchActivity;

    public static void launch(SearchActivity context, String shopsName, String goodsName) {
        searchActivity = context;
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra(SHOP_NAME, shopsName);
        intent.putExtra(GOOD_NAME, goodsName);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initInjector() {
        shopsName = getIntent().getStringExtra(SHOP_NAME);
        goodsName = getIntent().getStringExtra(GOOD_NAME);
        mActivityComponent.inject(this);
        if (!CheckDataIsEmpty.checkString(shopsName)) {
            flag = true;
        } else if (!CheckDataIsEmpty.checkString(goodsName)) {
            flag = false;
        }
    }


    public void onFinish(View v) {
        switch (v.getId()) {
            case R.id.back_sr:
                finish();
                searchActivity.finish();
                break;
            case R.id.cancel_sr:
                this.finish();
                break;
        }
    }

    @Override
    public void initViews() {
        initData();
        initRecyclerView();

    }

    /**
     * 初始化recycleView
     */
    private void initRecyclerView() {
        rv_sr.removeAllViews();
        if (flag) {
            shopList = new ArrayList<>();
            shopAroundAdapter = new ShopAroundAdapter(this, shopList);
            shopAroundAdapter.setOnItemClickListener(this);
            RecyclerViewHelper.initRecyclerViewV(this, rv_sr, true, shopAroundAdapter, 20, Color.RED);
        } else {
            goodsList = new ArrayList<>();
            shopGoodAdapter = new ShopGoodAdapter2(mActivity, goodsList);
            shopGoodAdapter.setOnItemClickListener(this);
            RecyclerViewHelper.initRecyclerViewG(this, rv_sr, true, shopAroundAdapter, 2, R.dimen.spacing_smaller);
        }
    }

    private void initData() {
        InitUtils.initSwipRefresh(sr_sr, this);
        if (flag) {
            et_search_content.setText(shopsName);
        } else {
            et_search_content.setText(goodsName);
        }
        mSearchResultPreter.setParams(shopsName, goodsName);
        mSearchResultPreter.attachView(this);
        mPresenter = mSearchResultPreter;
        mPresenter.onCreate();
    }

    @Override
    public void onItemClick(View view, int position) {

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
    public void onRefresh() {
        mSearchResultPreter.refreshData();
    }

    @Override
    public void setShopOrGoodList(SearchBeans searchBeans, @LoadNewsType.checker int loadType) {
        switch (loadType) {
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                if (flag) {
                    shopList = searchBeans.getShops();
                    shopAroundAdapter.updateItems(shopList);
                } else {
                    goodsList = searchBeans.getGoods();
                    shopGoodAdapter.updateItems(goodsList);
                }


                sr_sr.setRefreshing(false);
                break;
            case LoadNewsType.TYPE_REFRESH_ERROR:
                sr_sr.setRefreshing(false);
                break;
            case LoadNewsType.TYPE_LOAD_MORE_SUCCESS:
                break;
            case LoadNewsType.TYPE_LOAD_MORE_ERROR:
                break;
        }
    }
}
