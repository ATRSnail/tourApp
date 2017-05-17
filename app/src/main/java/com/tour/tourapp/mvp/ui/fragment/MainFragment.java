package com.tour.tourapp.mvp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
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
import com.tour.tourapp.entity.GoodBean;
import com.tour.tourapp.entity.RegionItem;
import com.tour.tourapp.entity.RspGoodsBean;
import com.tour.tourapp.entity.RspShopBean;
import com.tour.tourapp.entity.ShopBean;
import com.tour.tourapp.mvp.ui.activity.GoodDetailActivity;
import com.tour.tourapp.mvp.ui.activity.GoodListActivity;
import com.tour.tourapp.mvp.ui.activity.SearchActivity;
import com.tour.tourapp.mvp.ui.activity.ShopAroundActivity;
import com.tour.tourapp.utils.ImageLoader;
import com.tour.tourapp.utils.TransformUtils;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscriber;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/4/17
 */

public class MainFragment extends BaseLazyFragment implements AMap.OnMapLoadedListener
        , ClusterClickListener, ClusterRender, LocationSource {

    @BindView(R.id.map)
    MapView mMapView;
    private ImageView img_shop;
    private TextView tv_shop_name;
    @BindView(R.id.img_around)
    ImageView img_around;
    @BindView(R.id.img_search)
    ImageView img_search;

    private AMap mAMap;
    private ClusterOverlay mClusterOverlay;
    private int clusterRadius = 50;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    private double latitude, longitude;

    private PopupWindow popupWindow;
    private View popView;

    @Inject
    Activity mActivity;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        mMapView.onCreate(savedInstanceState);
        init();
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
        if (mAMap == null) {
            // 初始化地图
            mAMap = mMapView.getMap();
            //        mAMap.setOnMapLoadedListener(this);
            // 设置定位监听
            mAMap.setLocationSource(this);
            // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            mAMap.setMyLocationEnabled(true);
            // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
            mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        }
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
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        // mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            //  Toast.makeText(MainActivity.this, amapLocation.toStr(), Toast.LENGTH_SHORT).show();
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
        //根据控件的选择，重新设置定位参数
//        resetOption();
//        // 设置定位参数
//        locationClient.setLocationOption(locationOption);
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

    // 根据控件的选择，重新设置定位参数
    private void resetOption() {
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);
        // 设置是否开启缓存
        locationOption.setLocationCacheEnable(true);
        // 设置是否单次定位
        locationOption.setOnceLocation(true);
        //设置是否等待设备wifi刷新，如果设置为true,会自动变为单次定位，持续定位时不要使用
        locationOption.setOnceLocationLatest(true);
        //设置是否使用传感器
        locationOption.setSensorEnable(true);
        //设置是否开启wifi扫描，如果设置为false时同时会停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        String strInterval = "2000";
        if (!TextUtils.isEmpty(strInterval)) {
            try {
                // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
                locationOption.setInterval(Long.valueOf(strInterval));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        String strTimeout = "10000";
        if (!TextUtils.isEmpty(strTimeout)) {
            try {
                // 设置网络请求超时时间
                locationOption.setHttpTimeOut(Long.valueOf(strTimeout));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onMapLoaded() {

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
        RetrofitManager.getInstance(1).getshopGoodByIdObservable("8")
                .compose(TransformUtils.<RspGoodsBean>defaultSchedulers())
                .subscribe(new Subscriber<RspGoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RspGoodsBean rspGoodsBean) {
                        KLog.d("shop--->" + rspGoodsBean.toString());
                        fillDate(marker.getTitle(), rspGoodsBean.getBody().getGoods());
                    }
                });
    }

    private void fillDate(String shopName, List<GoodBean> goodBeen) {
        tv_shop_name.setText(shopName);
        ImageLoader.loadFit(getContext(), "", img_shop, R.mipmap.ic_launcher);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    private void initData(final double latitude, final double longitude) {
        KLog.d("shop--->" + latitude);
        RetrofitManager.getInstance(1).getShopsListObservable(latitude + "", longitude + "")
                .compose(TransformUtils.<RspShopBean>defaultSchedulers())
                .subscribe(new Subscriber<RspShopBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RspShopBean rspShopBean) {
                        shops = rspShopBean.getBody().getShops();
                        if (shops == null)
                            shops = new ArrayList<>();
                        shops.add(0, new ShopBean(latitude + "", longitude + ""));
                        KLog.d("shop--->" + rspShopBean.toString());
                        addShopMaker(shops, latitude, longitude);
                    }
                });
    }

    private List<ShopBean> shops;

    private void addShopMaker(final List<ShopBean> shops, final double latitude, final double longitude) {
        //添加测试数据
        new Thread() {
            public void run() {

                List<ClusterItem> items = new ArrayList<>();

                //随机10000个点
                for (int i = 0; i < shops.size(); i++) {
                    ShopBean shopBean = shops.get(i);
                    double lat = Double.valueOf(shopBean.getLats());
                    double lon = Double.valueOf(shopBean.getLongs());

                    LatLng latLng = new LatLng(lat, lon, false);
                    RegionItem regionItem = new RegionItem(latLng,
                            shopBean.getShopsName());
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
        img_shop = (ImageView) popView.findViewById(R.id.img_shop_1);
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
        img_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodDetailActivity.launch(mActivity, 9);
            }
        });

        tv_shop_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodListActivity.launch(mActivity, 9);
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
