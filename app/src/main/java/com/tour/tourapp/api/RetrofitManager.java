package com.tour.tourapp.api;

import android.text.TextUtils;
import android.util.SparseArray;

import com.socks.library.KLog;
import com.tour.tourapp.App;
import com.tour.tourapp.entity.RspGoodsBean;
import com.tour.tourapp.entity.RspShopBean;
import com.tour.tourapp.entity.RspShopDetailBean;
import com.tour.tourapp.entity.RspUserBean;
import com.tour.tourapp.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by long on 2016/8/22.
 * 整个网络通信服务的启动控制，必须先调用初始化函数才能正常使用网络通信接口
 */
public class RetrofitManager {

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    private NewsService mNewsService;

    private static SparseArray<RetrofitManager> sRetrofitManager = new SparseArray<>();
    private static volatile OkHttpClient sOkHttpClient;

    public RetrofitManager() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConstants.getHost())
                .client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        mNewsService = retrofit.create(NewsService.class);
    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                Cache cache = new Cache(new File(App.getAppContext().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder().cache(cache)
                            .connectTimeout(6, TimeUnit.SECONDS)
                            .readTimeout(6, TimeUnit.SECONDS)
                            .writeTimeout(6, TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor)
                            .build();
                }
            }
        }
        return sOkHttpClient;
    }

    public static RetrofitManager getInstance(int hostType) {
        RetrofitManager retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
            sRetrofitManager.put(hostType, retrofitManager);
            return retrofitManager;
        }
        return retrofitManager;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(App.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                KLog.d("no network");
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkAvailable(App.getAppContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            KLog.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            KLog.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };

    /************************************ API *******************************************/

    public Observable<RspShopBean> getShopsListObservable(String lats, String longs) {
        Map<String, String> map = new HashMap<>();
        map.put("lats", lats);
        map.put("longs", longs);
        KLog.d(map.toString());
        return mNewsService.getShopsList(map);
    }

    public Observable<RspUserBean> getLoginInObservable(String phoneNum, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNo", phoneNum);
        map.put("passW", password);
        KLog.a(map.toString());
        return mNewsService.loginIn(map);
    }

    public Observable<RspGoodsBean> getshopGoodByIdObservable(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        KLog.a(map.toString());
        return mNewsService.shopGoodById(map);
    }

    public Observable<RspShopDetailBean> getGoodsByShopId(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        KLog.a(map.toString());
        return mNewsService.getGoodsByShopId(map);
    }

    public Observable<RspShopBean> searchGoodsOrShop(String shopsName, String goodsName) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(shopsName))
            map.put("shopsName", shopsName);
        if (!TextUtils.isEmpty(goodsName))
            map.put("goodsName", goodsName);
        KLog.a(map.toString());
        return mNewsService.searchGoodsOrShop(map);
    }
}
