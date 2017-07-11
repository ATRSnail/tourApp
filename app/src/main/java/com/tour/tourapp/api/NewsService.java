package com.tour.tourapp.api;

import com.tour.tourapp.entity.RspGoodDetailBean;
import com.tour.tourapp.entity.RspGoodsBean;
import com.tour.tourapp.entity.RspSearchBean;
import com.tour.tourapp.entity.RspShopDetailBean;
import com.tour.tourapp.entity.RspStoreDetail;
import com.tour.tourapp.entity.RspUserAddBean;
import com.tour.tourapp.entity.RspUserAllAdd;
import com.tour.tourapp.entity.RspUserBean;
import com.tour.tourapp.entity.Rspclassify;
import com.tour.tourapp.entity.base.BaseRspObj;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

import static com.tour.tourapp.api.ApiConstants.Add_Address;
import static com.tour.tourapp.api.ApiConstants.Alter;
import static com.tour.tourapp.api.ApiConstants.Classify_First;
import static com.tour.tourapp.api.ApiConstants.Delete_Address;
import static com.tour.tourapp.api.ApiConstants.GOODS_IN_SHOP;
import static com.tour.tourapp.api.ApiConstants.Goods_Detail;
import static com.tour.tourapp.api.ApiConstants.LOGIN_IN_URL;
import static com.tour.tourapp.api.ApiConstants.NEARBY_SHOP;
import static com.tour.tourapp.api.ApiConstants.Regist;
import static com.tour.tourapp.api.ApiConstants.SEARCH_GOODS_SHOP;
import static com.tour.tourapp.api.ApiConstants.Search_Address;
import static com.tour.tourapp.api.ApiConstants.Select_Address;
import static com.tour.tourapp.api.ApiConstants.Shop_Detail;
import static com.tour.tourapp.api.ApiConstants.Update_Address;

/**
 * Created by long on 2016/8/22.
 * API 接口
 */
public interface NewsService {

    @FormUrlEncoded
    @POST(NEARBY_SHOP)
    Observable<RspSearchBean> getShopsList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Add_Address)
    Observable<BaseRspObj> addAddress(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Update_Address)
    Observable<BaseRspObj> updateAddress(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Search_Address)
    Observable<RspUserAddBean> searchAddress(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Delete_Address)
    Observable<BaseRspObj> deleteAddress(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Select_Address)
    Observable<RspUserAllAdd> selectAddress(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Shop_Detail)
    Observable<RspStoreDetail> shopDetail(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(GOODS_IN_SHOP)
    Observable<RspShopDetailBean> getGoodsByShopId(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Regist)
    Observable<BaseRspObj> regist(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(LOGIN_IN_URL)
    Observable<RspUserBean> loginIn(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Alter)
    Observable<BaseRspObj> alter(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(SEARCH_GOODS_SHOP)
    Observable<RspSearchBean> searchGoodsOrShop(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Goods_Detail)
    Observable<RspGoodDetailBean> goodsDetail(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Classify_First)
    Observable<Rspclassify> classify_First(@FieldMap Map<String, String> map);



    @FormUrlEncoded
    @POST(GOODS_IN_SHOP)
    Observable<RspGoodsBean> shopGoodById(@FieldMap Map<String, String> map);


}
