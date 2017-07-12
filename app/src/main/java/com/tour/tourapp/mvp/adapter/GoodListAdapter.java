package com.tour.tourapp.mvp.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tour.tourapp.R;
import com.tour.tourapp.entity.GoodsBean;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/2
 */

public class GoodListAdapter extends BaseQuickAdapter<GoodsBean> {


    public GoodListAdapter(int layoutResId, List<GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsBean item) {
       holder.setText(R.id.tv_title,item.getGoodsName());
    }

}
