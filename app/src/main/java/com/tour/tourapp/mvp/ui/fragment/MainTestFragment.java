package com.tour.tourapp.mvp.ui.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.dl7.recycler.divider.DividerItemDecoration;
import com.dl7.recycler.divider.RecyclerViewDivider;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.socks.library.KLog;
import com.tour.tourapp.App;
import com.tour.tourapp.ClusterOverlay;
import com.tour.tourapp.R;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.ClusterClickListener;
import com.tour.tourapp.entity.ClusterItem;
import com.tour.tourapp.entity.ClusterRender;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.entity.RegionItem;
import com.tour.tourapp.entity.RspGoodsBean;
import com.tour.tourapp.entity.RspNearbyShopBean;
import com.tour.tourapp.entity.RspShopAllGoodBean;
import com.tour.tourapp.entity.ShopDetailBean;
import com.tour.tourapp.mvp.adapter.RecommendGoodsAdapter;
import com.tour.tourapp.mvp.ui.activity.GoodDetailActivity;
import com.tour.tourapp.mvp.ui.activity.SearchActivity;
import com.tour.tourapp.mvp.ui.activity.ShopAroundActivity;
import com.tour.tourapp.utils.CheckDataIsEmpty;
import com.tour.tourapp.utils.DensityUtil;
import com.tour.tourapp.utils.ImageLoader;
import com.tour.tourapp.utils.TransformUtils;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscriber;

/**
 * 测试更改首页地图
 */

public class MainTestFragment extends BaseLazyFragment implements AMap.OnMarkerClickListener,
        AMap.OnMyLocationChangeListener {

    //定义了一个地图view
    @BindView(R.id.map)
    MapView mMapView;
    private ImageView close_popWin;
    private RecyclerView recommed_goods;
    private LinearLayout ll_popWin;
    private TextView tv_shop_name;
    private TextView tv_shop_address;
    @BindView(R.id.img_around)
    ImageView img_around;
    @BindView(R.id.img_search)
    ImageView img_search;

    //初始化地图控制器对象
    private AMap mAMap;
    //地图上 Marker 集合
    List<Marker> markList;


    private double latitude, longitude;

    private PopupWindow popupWindow;
    private View popView;

    private List<ShopDetailBean> shops;//附近商铺集合

    @Inject
    Activity mActivity;
    //标识，用于判断是否只显示一次定位信息和用户重新定位
    private boolean isFirstLoc = true;
    RecommendGoodsAdapter recommendGoodsAdapter;
    List<GoodsDetailBean> goodBeenTwo;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        init();
        initMap();
        initPop();
        initRecyclerView();
    }

    private void initRecyclerView() {
        //初始化 RecyclerView，多次初始化会影响  item和分割线之间 的距离
        goodBeenTwo = new ArrayList<>();
        RecyclerViewHelper.initRecyclerViewH(mActivity, recommed_goods,
                true, recommendGoodsAdapter, 20, Color.WHITE);
        recommendGoodsAdapter = new RecommendGoodsAdapter(mActivity, goodBeenTwo);
        recommed_goods.setAdapter(recommendGoodsAdapter);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onFirstUserVisible() {

    }

    private void init() {
        shops = new ArrayList<>();
        markList = new ArrayList<>();
        img_around.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopAroundActivity.launch(mActivity, latitude, longitude);
            }
        });
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.launch(mActivity);
            }
        });
    }


    private void initMap() {
        if (mAMap == null) {
            // 1.初始化地图
            mAMap = mMapView.getMap();

            //2.实现地图的定位蓝点----初始化定位蓝点样式类
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
            mAMap.setMyLocationStyle(myLocationStyle);
            myLocationStyle.showMyLocation(false);
            mAMap.setMyLocationEnabled(true);
            mAMap.setOnMyLocationChangeListener(this);
            mAMap.setOnMarkerClickListener(MainTestFragment.this);

        }
    }


    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();

        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }


    //视图移动到定位的地点，和设置缩放级别
    private void locationMap(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        LatLng latLng = new LatLng(latitude, longitude);

        //设置缩放级别
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(15.5f));
        //将地图移动到定位点
        mAMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng));

    }

    //给下方PopupWindow推荐商品布局GridView赋值
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void valueRecyclerView(List<GoodsDetailBean> goodBeen) {
        if (!CheckDataIsEmpty.checkList(goodBeen)) {

            goodBeenTwo.clear();
            //推荐商品两个，暂时取 商品 前两个
            goodBeenTwo.add(goodBeen.get(0));
            goodBeenTwo.add(goodBeen.get(1));
            // goodBeenTwo数据变更   recommendGoodsAdapter更新数据
            recommendGoodsAdapter.notifyDataSetChanged();
            openPopWindow();
        }
    }


    private void getNeatbyShopsData(final double latitude, final double longitude) {
        //获取 定位点 附近的商铺
        RetrofitManager.getInstance(1).getNearbyShops(latitude + "", longitude + "")
                .compose(TransformUtils.<RspNearbyShopBean>defaultSchedulers())
                .subscribe(new Subscriber<RspNearbyShopBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                    }

                    @Override
                    public void onNext(RspNearbyShopBean rspNearbyShopBean) {
                        shops = rspNearbyShopBean.getBody().getShops();

                        //商铺数量>1，添加Maker聚合点
                        if (!CheckDataIsEmpty.checkList(shops))
                            addMaker(shops);
                    }
                });
    }

    private void addMaker(List<ShopDetailBean> shops) {
        //清除之前所有Marker
        mAMap.clear();
        for (int i = 0; i < shops.size(); i++) {
            ShopDetailBean shopDetailBean = shops.get(i);
            double lat = Double.valueOf(shopDetailBean.getLats());
            double lon = Double.valueOf(shopDetailBean.getLongs());
            LatLng latLng = new LatLng(lat, lon);
            final Marker marker = mAMap.addMarker(new MarkerOptions().position(latLng)
                    .title(shopDetailBean.getShopsName()));
            markList.add(marker);
        }
    }


    private void initPop() {
        popView = LayoutInflater.from(getContext()).inflate(R.layout.layout_pop_shop, null);
        tv_shop_name = (TextView) popView.findViewById(R.id.tv_shop_name);
        tv_shop_address = (TextView) popView.findViewById(R.id.tv_shop_add);
        ll_popWin = (LinearLayout) popView.findViewById(R.id.ll_popWin);
        close_popWin = (ImageView) popView.findViewById(R.id.close_popWin);
        recommed_goods = (RecyclerView) popView.findViewById(R.id.recommed_goods);
        //设置弹出框的宽度和高度
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        close_popWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        ll_popWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UT.show("要看这家商铺的详情吗？");
            }
        });
    }

    /**
     * 按钮的监听
     */
    public void openPopWindow() {
        //从底部显示
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);
    }


    // 定义 Marker 点击事件监听
    @Override
    public boolean onMarkerClick(final Marker marker) {

        if (!CheckDataIsEmpty.checkList(shops) && !CheckDataIsEmpty.checkList(markList)) {
            final int index = markList.indexOf(marker);
            ShopDetailBean shopDetailBean = shops.get(index);
            tv_shop_name.setText(marker.getTitle());
            tv_shop_address.setText(shopDetailBean.getShopsAdds());
            //根据商铺id，查询商铺的商品信息
            RetrofitManager.getInstance(1).getGoodsByShopId(String.valueOf(shopDetailBean.getId()), "", "3", "1", "1000")
                    .compose(TransformUtils.<RspShopAllGoodBean>defaultSchedulers())
                    .subscribe(new Subscriber<RspShopAllGoodBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            KLog.e(e.toString());
                        }

                        @Override
                        public void onNext(RspShopAllGoodBean rspShopAllGoodBean) {
                            KLog.d(rspShopAllGoodBean.toString());
                            valueRecyclerView(rspShopAllGoodBean.getBody().getGoods());
                        }
                    });
        }


        return false;
    }


    @Override
    public void onMyLocationChange(Location location) {
        if (isFirstLoc) {
            KLog.a(location.toString());

            locationMap(location.getLatitude(), location.getLongitude());
            //获取附近商铺的数据
            getNeatbyShopsData(location.getLatitude(), location.getLongitude());
            isFirstLoc = false;
        }

    }


}
