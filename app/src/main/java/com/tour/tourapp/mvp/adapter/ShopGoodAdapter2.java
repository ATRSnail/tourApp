package com.tour.tourapp.mvp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.tour.tourapp.R;
import com.tour.tourapp.api.ApiConstants;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.utils.CheckDataIsEmpty;
import com.tour.tourapp.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * 分类---->商店商品的适配器
 *recyclerView
 */

public class ShopGoodAdapter2 extends BaseQuickAdapter<GoodsDetailBean> {


    public ShopGoodAdapter2(Context context, List<GoodsDetailBean> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.shopgood;
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsDetailBean item) {
        holder.setText(R.id.good_name, item.getGoodsName());
        holder.setText(R.id.good_price, item.getPriceS() + "");

        if (!CheckDataIsEmpty.checkList(item.getAtt())){
            ImageView imageView = holder.getView(R.id.good_icon);
            String attUrl = item.getAtt().get(0).getAttUrl();
            ImageLoader.loadImage(mContext,attUrl,imageView);
        }

    }

}
