package com.tour.tourapp.mvp.ui.fragment;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodBean;
import com.tour.tourapp.mvp.adapter.GoodListAdapter;
import com.tour.tourapp.mvp.presenter.impl.GoodListPresenterImpl;
import com.tour.tourapp.mvp.ui.activity.GoodDetailActivity;
import com.tour.tourapp.mvp.view.base.GoodListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class CateFragmentFragment extends BaseLazyFragment implements BaseQuickAdapter.OnRecyclerViewItemClickListener
        , GoodListView {

    @BindView(R.id.news_rv)
    RecyclerView news_rv;
    private GoodListAdapter goodListAdapter;
    private List<GoodBean> goodBeen;
    @Inject
    Activity mActivity;
    @Inject
    GoodListPresenterImpl mGoodListPreter;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initRecyclerView();
        initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_catefragment_list;
    }


    @Override
    public void onFirstUserVisible() {

    }

    /**
     * 初始化recycleView
     */
    private void initRecyclerView() {
        goodBeen = new ArrayList<>();
        goodListAdapter = new GoodListAdapter(R.layout.adapter_good_list, goodBeen);
        RecyclerViewHelper.initRecyclerViewG(mActivity, news_rv, true, goodListAdapter, 2, R.dimen.margin_decoration_note);
        goodListAdapter.setOnRecyclerViewItemClickListener(this);
        news_rv.setAdapter(goodListAdapter);
    }

    private void initData() {
        mGoodListPreter.setParams(10);
        mGoodListPreter.attachView(this);
        mPresenter = mGoodListPreter;
        mPresenter.onCreate();
    }

    @Override
    public void onItemClick(View view, int i) {
        GoodDetailActivity.launch(mActivity, 9);
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
    public void setAreaBeanList(List<GoodBean> areaBeanList, @LoadNewsType.checker int loadType) {
        goodListAdapter.setNewData(areaBeanList);
    }
}
