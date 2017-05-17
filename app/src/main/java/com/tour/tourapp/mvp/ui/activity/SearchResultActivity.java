package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.ShopBean;
import com.tour.tourapp.mvp.adapter.ShopAroundAdapter;
import com.tour.tourapp.mvp.presenter.impl.SearchResultPresenterImpl;
import com.tour.tourapp.mvp.view.base.ShopAroundView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SearchResultActivity extends BaseActivity implements OnRecyclerViewItemClickListener, ShopAroundView {

    private static final String SHOP_NAME = "shop_name";
    private static final String GOOD_NAME = "good_name";
    @BindView(R.id.news_rv)
    RecyclerView news_rv;
    @BindView(R.id.et_search_content)
    EditText et_search_content;
    @Inject
    Activity mActivity;
    @Inject
    SearchResultPresenterImpl mSearchResultPreter;

    private ShopAroundAdapter shopAroundAdapter;
    private List<ShopBean> shopBeen;
    private String shopsName, goodsName;

    public static void launch(Context context, String shopsName, String goodsName) {
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
    }

    @Override
    public void initViews() {
        initRecyclerView();
        initData();
        et_search_content.setText(shopsName + goodsName);
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

    }

    @Override
    public void setAreaBeanList(List<ShopBean> areaBeanList, @LoadNewsType.checker int loadType) {
        shopAroundAdapter.updateItems(areaBeanList);
    }
}
