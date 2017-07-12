package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.mvp.adapter.GoodListAdapter;
import com.tour.tourapp.mvp.presenter.impl.GoodListPresenterImpl;
import com.tour.tourapp.mvp.view.base.GoodListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class GoodListActivity extends BaseActivity implements GoodListView, BaseQuickAdapter.OnRecyclerViewItemClickListener {
    private static final String SHOP_ID = "shop_id";

    @BindView(R.id.news_rv)
    RecyclerView news_rv;
    @Inject
    Activity mActivity;
    @Inject
    GoodListPresenterImpl mGoodListPreter;
    private int id;
    private GoodListAdapter shopGoodAdapter;
    private List<GoodsDetailBean> goodBeen;

    public static void launch(Context context, int id) {
        Intent intent = new Intent(context, GoodListActivity.class);
        intent.putExtra(SHOP_ID, id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_detail;
    }

    @Override
    public void initInjector() {
        id = getIntent().getIntExtra(SHOP_ID, 0);
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
        goodBeen = new ArrayList<>();
        shopGoodAdapter = new GoodListAdapter(R.layout.adapter_good_list, goodBeen);
        RecyclerViewHelper.initRecyclerViewG(this, news_rv, true, shopGoodAdapter, 2,R.dimen.margin_decoration_note);
        shopGoodAdapter.setOnRecyclerViewItemClickListener(this);
        news_rv.setAdapter(shopGoodAdapter);
    }

    private void initData() {
        mGoodListPreter.setParams(id);
        mGoodListPreter.attachView(this);
        mPresenter = mGoodListPreter;
        mPresenter.onCreate();
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
    public void setAreaBeanList(List<GoodsDetailBean> areaBeanList, @LoadNewsType.checker int loadType) {
        shopGoodAdapter.setNewData(areaBeanList);
    }

    @Override
    public void onItemClick(View view, int position) {
        GoodDetailActivity.launch(mActivity,9);
    }
}
