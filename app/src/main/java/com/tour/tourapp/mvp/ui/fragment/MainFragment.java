package com.tour.tourapp.mvp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.socks.library.KLog;
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
import com.tour.tourapp.mvp.ui.activity.GoodDetailActivity;

import com.tour.tourapp.mvp.ui.activity.SearchActivity;
import com.tour.tourapp.mvp.ui.activity.ShopAroundActivity;
import com.tour.tourapp.utils.ImageLoader;
import com.tour.tourapp.utils.SHA1Utils;
import com.tour.tourapp.utils.TransformUtils;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscriber;

/**
 * 首页地图
 */
@Deprecated
public class MainFragment extends BaseLazyFragment implements ClusterClickListener, ClusterRender, LocationSource {

    //定义了一个地图view
    @BindView(R.id.map)
    MapView mMapView;
//    private ImageView img_shop;
    private TextView tv_shop_name;
    @BindView(R.id.img_around)
    ImageView img_around;
    @BindView(R.id.img_search)
    ImageView img_search;

    //初始化地图控制器对象
    private AMap mAMap;
    private ClusterOverlay mClusterOverlay;
    private int clusterRadius = 50;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption;
    private double latitude, longitude;

    private PopupWindow popupWindow;
    private View popView;

    private List<ShopDetailBean> shops;

    @Inject
    Activity mActivity;

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
        initLocation();
        startLocation();
        initPop();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onFirstUserVisible() {

    }

    private void init() {
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

    //实现地图的定位蓝点
    private void initMap() {
        if (mAMap == null) {
            // 初始化地图
            mAMap = mMapView.getMap();
            // 设置定位监听
            mAMap.setLocationSource(this);
            // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            mAMap.setMyLocationEnabled(true);
            // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
            mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        }


    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        locationOption = new AMapLocationClientOption();


        //可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        locationOption.setInterval(20000);

        return locationOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            KLog.a(amapLocation.toString());
            if (amapLocation.getErrorCode() == 0) {
                mapLoad(amapLocation.getLatitude(), amapLocation.getLongitude());
                Log.d("amapLocation", amapLocation.toStr());
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    };

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopLocation();
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
        destroyLocation();
    }

    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }


    private void mapLoad(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        mAMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(latitude, longitude), 14, 30, 0)));

        initData(latitude, longitude);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public Drawable getDrawAble(int clusterNum) {
        return null;
    }

    @Override
    public void onClick(final Marker marker, List<ClusterItem> clusterItems) {
        UT.show(marker.getTitle());
        openPopWindow();
        RetrofitManager.getInstance(1).getGoodsByShopId("8","","3","1","1000")
                .compose(TransformUtils.<RspShopAllGoodBean>defaultSchedulers())
                .subscribe(new Subscriber<RspShopAllGoodBean>() {
                    @Override
                    public void onCompleted() {
                        KLog.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                    }

                    @Override
                    public void onNext(RspShopAllGoodBean rspGoodsBean) {
                        KLog.d("shop--->" + rspGoodsBean.toString());
                        fillDate(marker.getTitle(), rspGoodsBean.getBody().getGoods());
                    }
                });
    }

    private void fillDate(String shopName, List<GoodsDetailBean> goodBeen) {
        tv_shop_name.setText(shopName);
//        ImageLoader.loadFit(getContext(), "", img_shop, R.mipmap.ic_launcher);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
    }

    @Override
    public void deactivate() {

    }

    private void initData(final double latitude, final double longitude) {
        KLog.d("shop--->" + latitude);
        RetrofitManager.getInstance(1).getNearbyShops(latitude + "", longitude + "")
                .compose(TransformUtils.<RspNearbyShopBean>defaultSchedulers())
                .subscribe(new Subscriber<RspNearbyShopBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RspNearbyShopBean rspNearbyShopBean) {
                        shops = rspNearbyShopBean.getBody().getShops();
                        if (shops == null)
                            shops = new ArrayList<>();
                        shops.add(0, new ShopDetailBean(latitude + "", longitude + ""));
                        KLog.d("shop--->" + rspNearbyShopBean.toString());
                        addShopMaker(shops, latitude, longitude);
                    }
                });
    }


    private void addShopMaker(final List<ShopDetailBean> shops, final double latitude, final double longitude) {
        //添加测试数据
        new Thread() {
            public void run() {

                List<ClusterItem> items = new ArrayList<>();

                //随机10000个点
                for (int i = 0; i < shops.size(); i++) {
                    ShopDetailBean shopDetailBean = shops.get(i);
                    double lat = Double.valueOf(shopDetailBean.getLats());
                    double lon = Double.valueOf(shopDetailBean.getLongs());

                    LatLng latLng = new LatLng(lat, lon, false);
                    RegionItem regionItem = new RegionItem(latLng,
                            shopDetailBean.getShopsName());
                    KLog.d("shop--->" + lat);
                    items.add(regionItem);
                }
                mClusterOverlay = new ClusterOverlay(mAMap, items,
                        dp2px(getContext(), clusterRadius),
                        getContext());
                mClusterOverlay.setClusterRenderer(MainFragment.this);
                mClusterOverlay.setOnClusterClickListener(MainFragment.this);

            }

        }.start();
    }

    private void initPop() {
        popView = LayoutInflater.from(getContext()).inflate(R.layout.layout_pop_shop, null);
        tv_shop_name = (TextView) popView.findViewById(R.id.tv_shop_name);
//        img_shop = (ImageView) popView.findViewById(R.id.img_shop_1);
        //设置弹出框的宽度和高度
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        img_shop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GoodDetailActivity.launch(mActivity, 9);
//            }
//        });

        tv_shop_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
