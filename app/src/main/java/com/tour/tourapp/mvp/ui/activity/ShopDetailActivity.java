package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dl7.recycler.divider.RecyclerViewDivider;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.socks.library.KLog;
import com.tour.tourapp.R;
import com.tour.tourapp.api.ApiConstants;
import com.tour.tourapp.api.LoadNewsType;

import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.entity.RspShopDetail;
import com.tour.tourapp.entity.ShopAllGoodBean;
import com.tour.tourapp.entity.ShopDetailBean;
import com.tour.tourapp.mvp.adapter.RecommendGoodsAdapter;
import com.tour.tourapp.mvp.adapter.ShopGoodAdapter2;
import com.tour.tourapp.mvp.presenter.impl.ShopDetailPresenterImpl;
import com.tour.tourapp.mvp.view.base.ShopDetailView;
import com.tour.tourapp.utils.CheckDataIsEmpty;
import com.tour.tourapp.utils.ImageLoader;
import com.tour.tourapp.utils.NetUtil;
import com.tour.tourapp.utils.TimeUtil;
import com.tour.tourapp.utils.TransformUtils;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscriber;

/**
 * 商铺详情页
 */
public class ShopDetailActivity extends BaseActivity implements ShopDetailView,
        OnRecyclerViewItemClickListener {
    private static final String SHOP_ID = "shop_id";

    @BindView(R.id.shop_icon)
    ImageView shop_icon;
    @BindView(R.id.shop_title)
    TextView shop_title;
    @BindView(R.id.shop_time)
    TextView shop_time;
    @BindView(R.id.shop_address)
    TextView shop_address;
    @BindView(R.id.shop_phone)
    TextView shop_phone;

    @BindView(R.id.rv_tj)
    RecyclerView recyclerView;//推荐商品
    @BindView(R.id.gv_goods)
    RecyclerView gridView;//所有商品

    @Inject
    Activity mActivity;


    @Inject
    ShopDetailPresenterImpl mGoodListPreter;


    private int id;//商铺的Id
    private ShopGoodAdapter2 shopGoodAdapter;
    private RecommendGoodsAdapter recommendGoodsAdapter;
    private List<GoodsDetailBean> goodsDetailBeanList;//商品列表
    List<GoodsDetailBean> goodsList;//推荐商铺列表

    public static void launch(Context context, int id) {
        Intent intent = new Intent(context, ShopDetailActivity.class);
        intent.putExtra(SHOP_ID, id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_shop_detail;
    }

    @Override
    public void initInjector() {
        id = getIntent().getIntExtra(SHOP_ID, 0);
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        setCustomTitle("店铺名称");
        initData();
        initGridView();
        initRecyclerView();


    }

    /**
     * 全部商品
     */
    private void initGridView() {
        goodsDetailBeanList = new ArrayList<>();
//        shopGoodAdapter = new ShopGoodAdapter(mActivity, goodsDetailBeanList);
//        gridView.setAdapter(shopGoodAdapter);


        GridLayoutManager manager = new GridLayoutManager(mActivity,2);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        gridView.setLayoutManager(manager);
        gridView.setHasFixedSize(true);
        gridView.setNestedScrollingEnabled(false);


        shopGoodAdapter = new ShopGoodAdapter2(mActivity, goodsDetailBeanList);
        gridView.addItemDecoration(new RecyclerViewDivider(mActivity,2,15,Color.RED));
        shopGoodAdapter.setOnItemClickListener(this);
        gridView.setAdapter(shopGoodAdapter);

    }


    /**
     * 初始化recycleView
     * 推荐商品  暂时从商铺全部商品取前两个
     */
    private void initRecyclerView() {
        goodsList = new ArrayList<>();
        recommendGoodsAdapter = new RecommendGoodsAdapter(mActivity, goodsList);
        recommendGoodsAdapter.setOnItemClickListener(this);
        RecyclerViewHelper.initRecyclerViewH(this, recyclerView, true, recommendGoodsAdapter, 2, Color.WHITE);

    }

    private void initData() {
        valueData();
        mGoodListPreter.setParams(id, 1, 1000, "", "2");
        mGoodListPreter.attachView(this);
        mPresenter = mGoodListPreter;
        mPresenter.onCreate();
    }


    @Override
    public void setAllGoodList(ShopAllGoodBean shopAllGoodBean, @LoadNewsType.checker int loadType) {
        switch (loadType) {
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                goodsDetailBeanList = shopAllGoodBean.getGoods();
//                shopGoodAdapter.setNewData(goodsDetailBeanList);
                shopGoodAdapter.updateItems(goodsDetailBeanList);
                if (!CheckDataIsEmpty.checkList(goodsDetailBeanList)) {

                    if (goodsDetailBeanList.size() >= 2) {
                        goodsList.clear();
                        goodsList.add(goodsDetailBeanList.get(0));
                        goodsList.add(goodsDetailBeanList.get(1));
                    } else {
                        goodsList.clear();
                        goodsList.add(goodsDetailBeanList.get(0));
                        goodsList.add(goodsDetailBeanList.get(0));
                    }

                }
                recommendGoodsAdapter.updateItems(goodsList);
                break;
            case LoadNewsType.TYPE_REFRESH_ERROR:
                break;
            case LoadNewsType.TYPE_LOAD_MORE_SUCCESS:


                break;
            case LoadNewsType.TYPE_LOAD_MORE_ERROR:

                break;
        }

    }


    private void valueData() {
        //根据id 请求商铺详情
        RetrofitManager.getInstance(1).shopDetail(id + "", "2")
                .compose(TransformUtils.<RspShopDetail>defaultSchedulers())
                .subscribe(new Subscriber<RspShopDetail>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                    }

                    @Override
                    public void onNext(RspShopDetail rspShopDetail) {
                        if ("0".equals(rspShopDetail.getHead().getRspCode())) {
                            ShopDetailBean shop = rspShopDetail.getBody().getShop();

                            String attUrl =  shop.getShopsAtt().get(0).getAttUrl();
                            ImageLoader.loadImage(mActivity,attUrl,shop_icon);



                            shop_title.setText(shop.getShopsName());
                            shop_time.setText(TimeUtil.dateString(shop.getCtime()));
                            shop_address.setText(shop.getShopsAdds());
                            shop_phone.setText(TimeUtil.dateString(shop.getCtime()));
                        }
                    }
                });
    }


    @Override
    public void onItemClick(View view, int position) {
        if (!CheckDataIsEmpty.checkList(goodsDetailBeanList)){
            GoodDetailActivity.launch(mActivity, goodsDetailBeanList.get(position).getId());
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


}
