package com.tour.tourapp.mvp.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tour.tourapp.R;
import com.tour.tourapp.entity.GoodBean;

import java.util.List;

/**
 * @author xch
 * @version 1.0
 * @create_date 2017/5/2
 */

public class GoodListAdapter extends BaseQuickAdapter<GoodBean> {


    public GoodListAdapter(int layoutResId, List<GoodBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodBean item) {
       holder.setText(R.id.tv_title,item.getGoodsName());
    }

}
