package com.tour.tourapp.mvp.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.socks.library.KLog;
import com.tour.tourapp.R;
import com.tour.tourapp.api.ApiConstants;
import com.tour.tourapp.entity.ShopDetailBean;
import com.tour.tourapp.utils.ImageLoader;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/2
 */

public class ShopAroundAdapter extends BaseQuickAdapter<ShopDetailBean> {

    public ShopAroundAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_shops_item;
    }

    @Override
    protected void convert(BaseViewHolder holder, ShopDetailBean item) {
        holder.setText(R.id.shop_title, item.getShopsName());
        holder.setText(R.id.shop_address, item.getShopsAdds());
        holder.setText(R.id.shop_distance, String.valueOf(item.getDistance()));

        ImageView imageView = holder.getView(R.id.shop_icon);
        String attUrl =  item.getShopsAtt().get(0).getAttUrl();
        ImageLoader.loadImage(mContext,attUrl,imageView);


//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
//        int width = (ScreenUtils.getScreenWidth(mContext) - DensityUtil.dip2px(mContext, 30)) / 2;
//        params.width = width;
//        imageView.setLayoutParams(params);
    }

}
