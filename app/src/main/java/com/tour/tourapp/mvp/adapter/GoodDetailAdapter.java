package com.tour.tourapp.mvp.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.tour.tourapp.R;
import com.tour.tourapp.api.ApiConstants;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.utils.ImageLoader;

import java.util.List;

/**
 * Created by ATRSnail on 2017/7/24.
 */

public class GoodDetailAdapter extends BaseQuickAdapter<GoodsDetailBean> {

    public GoodDetailAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_item_gd;
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsDetailBean item) {
        ImageView imageView = holder.getView(R.id.img_gd);
        String attUrl =  item.getAtt().get(0).getAttUrl();
        ImageLoader.loadImage(mContext,attUrl,imageView);
    }
}
