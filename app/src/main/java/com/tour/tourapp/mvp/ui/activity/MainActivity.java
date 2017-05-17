package com.tour.tourapp.mvp.ui.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

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
import com.tour.tourapp.entity.RegionItem;
import com.tour.tourapp.entity.RspShopBean;
import com.tour.tourapp.entity.ShopBean;
import com.tour.tourapp.utils.TransformUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
@Deprecated
public class MainActivity extends CheckPermissionsActivity implements AMap.OnMapLoadedListener
        , ClusterClickListener, ClusterRender, LocationSource {
    private MapView mMapView;
    private AMap mAMap;
    private ClusterOverlay mClusterOverlay;
    private int clusterRadius = 100;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        init();
        initLocation();
        startLocation();
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
            //点击可以动态添加点
            mAMap.setOnMapClickListener(new AMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    double lat = Math.random() + 39.474923;
                    double lon = Math.random() + 116.027116;

                    LatLng latLng1 = new LatLng(lat, lon, false);
                    RegionItem regionItem = new RegionItem(latLng1,
                            "test");
                    mClusterOverlay.addClusterItem(regionItem);

                }
            });
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
        locationClient = new AMapLocationClient(this.getApplicationContext());
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
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
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
    //location Error, ErrCode:10, errInfo:定位服务启动失败 请到http://lbs.amap.com/api/android-location-sdk/guide/utilities/errorcode/查看错误码说明,错误详细信息:请检查配置文件是否配置服务
      //      04-17 14:39:38.343 23825-23825/com.tour.tourapp E/AmapError:

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
    public void onClick(Marker marker, List<ClusterItem> clusterItems) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }

    private void initData(final double latitude, final double longitude) {
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

                        KLog.d("shop--->"+rspShopBean.toString());
                        addShopMaker(rspShopBean.getBody().getShops(), latitude, longitude);
                    }
                });
    }

    private void addShopMaker(final List<ShopBean> shops, final double latitude, final double longitude) {
        KLog.d("shop--->"+shops.toString());
        //添加测试数据
        new Thread() {
            public void run() {

                List<ClusterItem> items = new ArrayList<>();
                shops.add(0,new ShopBean(latitude+"",longitude+""));
                //随机10000个点
                for (int i = 0; i < shops.size(); i++) {

                    double lat = Double.valueOf(shops.get(i).getLats());
                    double lon = Double.valueOf(shops.get(i).getLongs());

                    LatLng latLng = new LatLng(lat, lon, false);
                    RegionItem regionItem = new RegionItem(latLng,
                            "test" + i);
                    items.add(regionItem);

                }
                mClusterOverlay = new ClusterOverlay(mAMap, items,
                        dp2px(getApplicationContext(), clusterRadius),
                        getApplicationContext());
                mClusterOverlay.setClusterRenderer(MainActivity.this);
                mClusterOverlay.setOnClusterClickListener(MainActivity.this);

            }

        }.start();
    }
}
