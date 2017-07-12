package com.tour.tourapp.api;

import android.text.TextUtils;
import android.util.SparseArray;

import com.socks.library.KLog;
import com.tour.tourapp.App;
import com.tour.tourapp.entity.RspCartBean;
import com.tour.tourapp.entity.RspGoodDetailBean;
import com.tour.tourapp.entity.RspShopAllGoodBean;
import com.tour.tourapp.entity.RspGoodsBean;
import com.tour.tourapp.entity.RspOrderBean;
import com.tour.tourapp.entity.RspNearbyShopBean;
import com.tour.tourapp.entity.RspShopDetail;
import com.tour.tourapp.entity.RspUserAddBean;
import com.tour.tourapp.entity.RspUserAllAdd;
import com.tour.tourapp.entity.RspUserBean;
import com.tour.tourapp.entity.Rspclassify;
import com.tour.tourapp.entity.base.BaseRspObj;
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

    public Observable<RspNearbyShopBean> getShopsListObservable(String lats, String longs) {
        Map<String, String> map = new HashMap<>();
        map.put("lats", lats);
        map.put("longs", longs);
        KLog.a(map.toString());
        return mNewsService.getShopsList(map);
    }

    public Observable<BaseRspObj> addAddress(String reName, String rePhone, String adds, String aPPuserId) {
        Map<String, String> map = new HashMap<>();
        map.put("reName", reName);
        map.put("rePhone", rePhone);
        map.put("adds", adds);
        map.put("aPPuserId", aPPuserId);
        KLog.a(map.toString());
        return mNewsService.addAddress(map);
    }

    /**
     *
     * @param reName  收件人姓名
     * @param rePhone  收件人电话
     * @param adds  收件人地址
     * @param defaultAddress  设置默认地址 0是非默认, 1 是默认状态
     * @param id
     * @return
     */
    public Observable<BaseRspObj> updateAddress(String reName, String rePhone, String adds, String defaultAddress, String id) {
        Map<String, String> map = new HashMap<>();
        map.put("reName", reName);
        map.put("rePhone", rePhone);
        map.put("adds", adds);
        map.put("defaultAddress", defaultAddress);
        map.put("id", id);
        KLog.a(map.toString());
        return mNewsService.updateAddress(map);
    }

    public Observable<RspUserAddBean> lookAddress(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        KLog.a(map.toString());
        return mNewsService.lookAddress(map);
    }

    public Observable<BaseRspObj> deleteAddress(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        KLog.a(map.toString());
        return mNewsService.deleteAddress(map);
    }

    /**
     *
     * @param aPPUserId 手机用户id
     * @return
     */
    public Observable<RspUserAllAdd> selectAddress(String aPPUserId) {
        Map<String, String> map = new HashMap<>();
        map.put("aPPUserId", aPPUserId);
        KLog.a(map.toString());
        return mNewsService.selectAddress(map);
    }


    /**
     * @param id      商铺id
     * @param attType 不填写就是查询所有，建议传入
     *                附件类型 1经营证件图片 2店铺缩略图 3店铺介绍图(多张) 4 office文件
     * @return
     */
    public Observable<RspShopDetail> shopDetail(String id, String attType) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        if (!TextUtils.isEmpty(attType) && "".equals(attType))
            map.put("attType", attType);
        KLog.a(map.toString());
        return mNewsService.shopDetail(map);
    }

    /**
     * @param id        商铺id
     * @param goodsType 商品类别，非必需
     * @param attType   附件类型
     * @param page      页数
     * @param size      条数
     * @return
     */
    public Observable<RspShopAllGoodBean> getGoodsByShopId(String id, String goodsType, String attType, String page, String size) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        if (!TextUtils.isEmpty(goodsType) && "".equals(goodsType))
            map.put("goodsType", goodsType);
        map.put("attType", attType);
        map.put("page", page);
        map.put("size", size);
        KLog.a(map.toString());
        return mNewsService.getGoodsByShopId(map);
    }

    /**
     * @param phoneNo
     * @param passW
     * @param nickName 非必需
     * @param email    非必需
     * @return
     */
    public Observable<BaseRspObj> regist(String phoneNo, String passW, String nickName, String email) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNo", phoneNo);
        map.put("passW", passW);
        map.put("nickName", nickName);
        map.put("email", email);
        KLog.a(map.toString());
        return mNewsService.regist(map);
    }

    public Observable<RspUserBean> getLoginInObservable(String phoneNum, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNo", phoneNum);
        map.put("passW", password);
        KLog.a(map.toString());
        return mNewsService.loginIn(map);
    }

    /**
     * @param phoneNum    手机号
     * @param password    新密码
     * @param oldPassword 旧密码
     * @param nickName    昵称
     * @param id          用户Id
     * @param email       邮箱
     * @return
     */
    public Observable<RspUserBean> alter(String phoneNum, String password
            , String oldPassword, String nickName, String id, String email) {
        Map<String, String> map = new HashMap<>();
        map.put("phoneNo", phoneNum);
        map.put("passW", password);
        map.put("oldPassword", oldPassword);
        map.put("nickName", nickName);
        map.put("id", id);
        map.put("email", email);
        KLog.a(map.toString());
        return mNewsService.loginIn(map);
    }

    /**
     * @param shopsName 商店名称
     * @param goodsName 商品名称
     *                  注意两参数传一个即可
     * @return
     */
    public Observable<RspNearbyShopBean> searchGoodsOrShop(String shopsName, String goodsName) {
        //注意两参数传一个即可
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(shopsName) && "".equals(shopsName))
            map.put("shopsName", shopsName);
        if (!TextUtils.isEmpty(goodsName) && "".equals(goodsName))
            map.put("goodsName", goodsName);
        KLog.a(map.toString());
        return mNewsService.searchGoodsOrShop(map);
    }

    /**
     * @param id 商品id
     * @return
     */
    public Observable<RspGoodDetailBean> goodsDetail(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        KLog.a(map.toString());
        return mNewsService.goodsDetail(map);


    }

    public Observable<Rspclassify> classify_First() {
        Map<String, String> map = new HashMap<>();
        KLog.a(map.toString());
        return mNewsService.classify_First(map);
    }

    // 按类型查找商品 并且选择商品的排列循序

    /**
     * @param goodsType 商品的类型  此参数是分类栏目的code字段值(children里面的code)
     * @param orderBy   商品的排序   0 是按价格高-低排序 2是价格低-高 1 是库存量
     * @param parent    二级节点的父节点(二级目录的所有选项)  此字段 是children里面的parent
     *                  goodsType 和parent 两个参数只能传一个参数即可
     *                  Parent 是二级目录的所有选项,
     *                  goodsType 是二级目录的某一个子选项
     * @return
     */
    public Observable<Rspclassify> classify_First(String goodsType, String orderBy, String parent) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(goodsType) && !"".equals(goodsType))
            map.put("goodsType", goodsType);
        if (!TextUtils.isEmpty(orderBy) && !"".equals(orderBy))
            map.put("orderBy", orderBy);
        if (!TextUtils.isEmpty(parent) && !"".equals(parent))
            map.put("parent", parent);
        KLog.a(map.toString());
        return mNewsService.classify_First(map);
    }

    /**
     * @param goodsId 商品的id
     * @param userId  App用户的id
     * @return
     */
    public Observable<BaseRspObj> addCart(String goodsId, String userId) {
        Map<String, String> map = new HashMap<>();
        map.put("goodsId", goodsId);
        map.put("userId", userId);
        KLog.a(map.toString());
        return mNewsService.addCart(map);
    }

    /**
     * @param id       购物车内商品的id
     * @param quantity 商品的数量
     * @return
     */
    public Observable<BaseRspObj> updateCart(String id, String quantity) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("quantity", quantity);
        KLog.a(map.toString());
        return mNewsService.updateCart(map);
    }

    /**
     * @param id 购物车内商品的id
     * @return
     */
    public Observable<BaseRspObj> deleteCart(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        KLog.a(map.toString());
        return mNewsService.deleteCart(map);
    }


    public Observable<RspCartBean> queryCart(String userId) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        KLog.a(map.toString());
        return mNewsService.queryCart(map);
    }

    /**
     * @param cartIds   购物车内商品的id  注:购物车id的数组  使用String类型 用,隔开
     * @param addressId 收货地址id
     * @return
     */
    public Observable<BaseRspObj> addOrder(String cartIds, String addressId) {
        Map<String, String> map = new HashMap<>();
        map.put("cartIds", cartIds);
        map.put("addressId", addressId);
        KLog.a(map.toString());
        return mNewsService.addOrder(map);
    }


    public Observable<BaseRspObj> updateOrder(String orderId, String orderState) {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("orderState", orderState);
        KLog.a(map.toString());
        return mNewsService.updateOrder(map);
    }

    /**
     * @param orderInfoId 订单的id
     * @return
     */
    public Observable<BaseRspObj> deleteOrder(String orderInfoId) {
        Map<String, String> map = new HashMap<>();
        map.put("orderInfoId", orderInfoId);
        KLog.a(map.toString());
        return mNewsService.deleteOrder(map);
    }

    /**
     * @param appUserId  App用户的id
     * @param orderState 订单的状态  1已付款0 未付款
     * @return
     */
    public Observable<RspOrderBean> queryOrder(String appUserId, String orderState) {
        Map<String, String> map = new HashMap<>();
        map.put("appUserId", appUserId);
        map.put("orderState", orderState);
        KLog.a(map.toString());
        return mNewsService.queryOrder(map);
    }


    public Observable<RspGoodsBean> getshopGoodByIdObservable(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        KLog.a(map.toString());
        return mNewsService.shopGoodById(map);
    }
}
