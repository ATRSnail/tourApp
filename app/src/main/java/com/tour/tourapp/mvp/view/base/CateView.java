package com.tour.tourapp.mvp.view.base;

import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodsClassify;
import com.tour.tourapp.entity.GoodsDetailBean;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/3
 */

public interface CateView extends BaseView{

    void setShopGoodList(List<GoodsDetailBean> shopGoodBeen, @LoadNewsType.checker int loadType);

    void setGoodsClassifyList(List<GoodsClassify> goodsClassify, @LoadNewsType.checker int loadType);
}
