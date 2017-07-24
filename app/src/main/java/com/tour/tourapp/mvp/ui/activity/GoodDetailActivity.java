package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.recycler.helper.RecyclerViewHelper;
import com.socks.library.KLog;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodDetailBeans;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.mvp.adapter.GoodDetailAdapter;
import com.tour.tourapp.mvp.presenter.impl.GoodInfoPresenterImpl;
import com.tour.tourapp.mvp.view.base.GoodInfoView;
import com.tour.tourapp.utils.ImageLoader;
import com.tour.tourapp.utils.NetUtil;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 商品详情页
 */
public class GoodDetailActivity extends BaseActivity implements GoodInfoView {

    @BindView(R.id.icon_gd)
    ImageView icon_gd;
    @BindView(R.id.name_gd)
    TextView name_gd;
    @BindView(R.id.like_gd)
    ImageView like_gd;
    @BindView(R.id.freight_gd)
    TextView freight_gd;
    @BindView(R.id.discount_gd)
    TextView discount_gd;
    @BindView(R.id.price_gd)
    TextView price_gd;
    @BindView(R.id.info_gd)
    TextView info_gd;

    @BindView(R.id.rv_gd)
    RecyclerView rv_gd;

    @Inject
    Activity mActivity;

    @Inject
    GoodInfoPresenterImpl goodInfoPresenter;

    private static final String GOOD_ID = "good_id";
    int id;//商品的Id

    List<GoodsDetailBean> goodsDetailBeanList;
    GoodDetailAdapter goodDetailAdapter;

    public static void launch(Context context, int goodId) {
        Intent intent = new Intent(context, GoodDetailActivity.class);
        intent.putExtra(GOOD_ID, goodId);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_good_detail;
    }

    @Override
    public void initInjector() {
        id = getIntent().getIntExtra(GOOD_ID, 0);
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        setCustomTitle("商品详情");
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        goodsDetailBeanList = new ArrayList<>();
        goodDetailAdapter = new GoodDetailAdapter(mActivity, goodsDetailBeanList);
        RecyclerViewHelper.initRecyclerViewH(mActivity, rv_gd, true, goodDetailAdapter,
                20, Color.RED);

    }

    private void initData() {
        goodInfoPresenter.setParams(id);
        goodInfoPresenter.attachView(this);
        mPresenter = goodInfoPresenter;
        mPresenter.onCreate();
    }

    @Override
    public void setGoodInfo(GoodDetailBeans goodDetailBeans, @LoadNewsType.checker int loadType) {
        switch (loadType) {
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                goodsDetailBeanList = goodDetailBeans.getShopGoods();
                goodDetailAdapter.updateItems(goodsDetailBeanList);
                valueData(goodDetailBeans.getShopGoods().get(0));
                break;
            case LoadNewsType.TYPE_REFRESH_ERROR:
                break;
            case LoadNewsType.TYPE_LOAD_MORE_SUCCESS:
                break;
            case LoadNewsType.TYPE_LOAD_MORE_ERROR:
                break;

        }
    }

    private void valueData(GoodsDetailBean goodsDetailBean) {
        String attUrl = goodsDetailBean.getAtt().get(0).getAttUrl();
        ImageLoader.loadImage(mActivity,attUrl,icon_gd);
        name_gd.setText(goodsDetailBean.getGoodsName().trim());
        freight_gd.setText("运费： 包邮");
        price_gd.setText(String.valueOf(goodsDetailBean.getPriceS()));
        // 设置中划线并加清晰
        price_gd.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        discount_gd.setText(String.valueOf(goodsDetailBean.getPriceS()));
        info_gd.setText(goodsDetailBean.getGoodsInfo());
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


}
