package com.tour.tourapp.mvp.adapter;


import android.content.Context;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.tour.tourapp.R;
import com.tour.tourapp.entity.ShopBean;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/2
 */

public class ShopAroundAdapter extends BaseQuickAdapter<ShopBean> {

    public ShopAroundAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_news_list;
    }

    @Override
    protected void convert(BaseViewHolder holder, ShopBean item) {
       holder.setText(R.id.tv_title,item.getShopsName());

    }

}
