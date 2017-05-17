package com.tour.tourapp.api;

import com.tour.tourapp.entity.RspGoodsBean;
import com.tour.tourapp.entity.RspShopBean;
import com.tour.tourapp.entity.RspShopDetailBean;
import com.tour.tourapp.entity.RspUserBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

import static com.tour.tourapp.api.ApiConstants.GOODS_IN_SHOP;
import static com.tour.tourapp.api.ApiConstants.LOGIN_IN_URL;
import static com.tour.tourapp.api.ApiConstants.NEWS_URL;
import static com.tour.tourapp.api.ApiConstants.SEARCH_GOODS_SHOP;
import static com.tour.tourapp.api.ApiConstants.SHOPS_GOOD;
import static com.tour.tourapp.api.ApiConstants.SHOW_ONE;

/**
 * Created by long on 2016/8/22.
 * API 接口
 */
public interface NewsService {

    @FormUrlEncoded
    @POST(NEWS_URL)
    Observable<RspShopBean> getShopsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(LOGIN_IN_URL)
    Observable<RspUserBean> loginIn(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(SHOPS_GOOD)
    Observable<RspGoodsBean> shopGoodById(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(SHOW_ONE)
    Observable<RspGoodsBean> showOne(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(GOODS_IN_SHOP)
    Observable<RspShopDetailBean> getGoodsByShopId(@FieldMap Map<String, String> map);



    @FormUrlEncoded
    @POST(SEARCH_GOODS_SHOP)
    Observable<RspShopBean> searchGoodsOrShop(@FieldMap Map<String, String> map);


}
