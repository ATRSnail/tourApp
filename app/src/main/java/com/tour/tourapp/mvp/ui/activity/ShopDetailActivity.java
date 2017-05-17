package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodBean;
import com.tour.tourapp.entity.ShopDetailBean;
import com.tour.tourapp.mvp.adapter.GoodListAdapter;
import com.tour.tourapp.mvp.presenter.impl.ShopDetailPresenterImpl;
import com.tour.tourapp.mvp.view.base.ShopDetailView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 商铺详情页
 */
public class ShopDetailActivity extends BaseActivity implements ShopDetailView, BaseQuickAdapter.OnRecyclerViewItemClickListener {
    private static final String SHOP_ID = "shop_id";
    @BindView(R.id.news_rv)
    RecyclerView news_rv;
    @Inject
    Activity mActivity;
    @Inject
    ShopDetailPresenterImpl mGoodListPreter;
    private ImageView img_shop;
    private TextView tv_title;
    private TextView tv_address;
    private View shopDateView;
    private int id;
    private GoodListAdapter goodListAdapter;
    private List<GoodBean> goodBeen;

    public static void launch(Context context, int id) {
        Intent intent = new Intent(context, ShopDetailActivity.class);
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
        initRecHeadView();
        initRecyclerView();
        initData();
    }

    private void initRecHeadView() {
        shopDateView = LayoutInflater.from(mActivity).inflate(R.layout.layout_shop_detail_header,null);
        img_shop = (ImageView) shopDateView.findViewById(R.id.img_shop);
        tv_title = (TextView) shopDateView.findViewById(R.id.tv_title);
        tv_address = (TextView) shopDateView.findViewById(R.id.tv_address);

    }

    /**
     * 初始化recycleView
     */
    private void initRecyclerView() {
        goodBeen = new ArrayList<>();
        goodListAdapter = new GoodListAdapter(R.layout.adapter_good_list, goodBeen);
        RecyclerViewHelper.initRecyclerViewG(this, news_rv, true, goodListAdapter, 2,R.dimen.margin_decoration_note,true);
        goodListAdapter.setOnRecyclerViewItemClickListener(this);
        goodListAdapter.addHeaderView(shopDateView);
        news_rv.setAdapter(goodListAdapter);
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
    public void setAreaBeanList(ShopDetailBean shopDetailBean, @LoadNewsType.checker int loadType) {
        goodListAdapter.setNewData(shopDetailBean.getGoods());
        tv_title.setText(shopDetailBean.getShops().getShopsName());
        tv_address.setText(shopDetailBean.getShops().getShopsAdds());
    }

    @Override
    public void onItemClick(View view, int position) {
        GoodDetailActivity.launch(mActivity,9);
    }

}
