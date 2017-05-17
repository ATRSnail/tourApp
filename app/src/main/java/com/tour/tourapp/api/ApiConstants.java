package com.tour.tourapp.api;

/**
 * Created by long on 2016/8/31.
 * 新闻常量
 */
public final class ApiConstants {

    //线下
    public static final String NETEAST_HOST = "http://172.16.10.15:9806/";
    public static final String NETEAST_IMG_HOST = "http://172.16.10.15:9400/";

    //线上
//    public static final String NETEAST_HOST = "http://59.108.94.40:9100/";

    public static String getHost() {
        return NETEAST_HOST;
    }

    public static final String NEWS_URL = "/ds-api/shops/listShops";

    public static final String LOGIN_IN_URL = "ds-api/appUser/login";

    //根据商铺id查询该商铺下所有商品或以类别区分
    public static final String SHOPS_GOOD = "ds-api/shopsGood/getGoodsByShopId/";

    //根据商铺id，查询商铺的详细信息
    public static final String SHOW_ONE = "ds-api/shops/showOne/";

    public static final String GOODS_IN_SHOP= "ds-api/shopsGood/getGoodsByShopId/";

    public static final String SEARCH_GOODS_SHOP= "ds-api/shopsGood/serchGoodsOrShop/";

}
