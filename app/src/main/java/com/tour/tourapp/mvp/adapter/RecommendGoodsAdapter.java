package com.tour.tourapp.mvp.adapter;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.socks.library.KLog;
import com.tour.tourapp.R;
import com.tour.tourapp.api.ApiConstants;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.utils.DensityUtil;
import com.tour.tourapp.utils.ImageLoader;
import com.tour.tourapp.utils.ScreenUtils;


import java.util.List;


/**
 * 首页 点击商铺弹出 PopUpWindows里面 推荐商铺 的适配器
 */

public class RecommendGoodsAdapter extends BaseQuickAdapter<GoodsDetailBean> {


    public RecommendGoodsAdapter(Context context, List<GoodsDetailBean> data) {
        super(context, data);
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.pop_recom_goods;
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsDetailBean item) {
        ImageView imageView = holder.getView(R.id.img_recom);
        String attUrl = item.getAtt().get(0).getAttUrl();

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        int width = (ScreenUtils.getScreenWidth(mContext) - DensityUtil.dip2px(mContext, 30)) / 2;
        params.width = width;
        imageView.setLayoutParams(params);
        ImageLoader.loadImage(mContext,attUrl,imageView);

        holder.setText(R.id.name_recomgoods, item.getGoodsName());
    }
}
