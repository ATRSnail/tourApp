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

    public static final String NEARBY_SHOP = "/ds-api/shops/listShops";//根据你的定位获取附近的商铺

    public static final String Add_Address = "/ds-api/appUserAdds/addAddress";//新增收货地址

    public static final String Update_Address = "/ds-api/appUserAdds/updateAddress";//修改收货地址及更改默认地址

    public static final String Look_Address = "/ds-api/appUserAdds/lookAddress";//根据收货地址id  查询收货地址

    public static final String Delete_Address = "/ds-api/appUserAdds/deleteAddress";//删除收件地址

    public static final String Select_Address = "/ds-api/appUserAdds/selectAddress";//根据用户的id查询收件地址

    //根据商铺id，查询商铺的详细信息
    public static final String SHOP_Detail = "ds-api/shops/showOne";

    //根据商铺id查询该商铺下所有商品或以类别区分
    public static final String GOODS_IN_SHOP = "ds-api/shopsGood/getGoodsByShopId/";

    public static final String Regist = "ds-api/appUser/regist";

    public static final String LOGIN_IN_URL = "ds-api/appUser/login";

    public static final String Alter = "ds-api/appUser/modi";

    public static final String SEARCH_GOODS_SHOP = "ds-api/shopsGood/serchGoodsOrShop";

    //根据商品的id查询商品的详细信息
    public static final String Goods_Detail = "ds-api/shopsGood/lookGoods";

    public static final String Classify_First = "ds-api/classify/classifyGoods";//分类首页接口

    //添加到购物车
    public static final String Add_Cart = "ds-api/cart/addCart";

    //修改购物车的某件商品的数量
    public static final String Update_Cart = "ds-api/cart/updateCart";

    //删除购物车的商品
    public static final String Delete_Cart = "ds-api/cart/deleteCart";

    //查看购物车的商品
    public static final String QUERY_Cart = "ds-api/cart/selectCart";


    //添加订单
    public static final String Add_Order = "ds-api/order/addOrder";

    //删除订单
    public static final String Delete_Order = "ds-api/order/deleteOrder";

    //查询订单
    public static final String QUERY_ORDER = "ds-api/order/listByOrder";

    //修改订单
    public static final String UPDATE_ORDER = "ds-api/order/updateOrder";

}
