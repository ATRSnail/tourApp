package com.tour.tourapp.mvp.adapter;


import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tour.tourapp.R;
import com.tour.tourapp.entity.GoodsClassify;

import java.util.List;

/**
 * 分类-----侧栏分类商品的数据适配器
 */

public class GoodsClassifyAdapter extends BaseQuickAdapter<GoodsClassify> {
    int onClickPostion = -1;

    public GoodsClassifyAdapter(int layoutResId, List<GoodsClassify> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, GoodsClassify item) {
        TextView textView = holder.getView(R.id.classify_name);
        textView.setText(item.getName());
        if (onClickPostion == holder.getAdapterPosition()) {
           textView.setTextColor(Color.WHITE);
            textView.setBackgroundColor(Color.parseColor("#4aca8f"));
        }else {
            textView.setTextColor(Color.parseColor("#4aca8f"));
            textView.setBackgroundColor(Color.WHITE);
        }
    }

    public void setOnClickPostion(int onClickPostion) {
        this.onClickPostion = onClickPostion;
        notifyDataSetChanged();
    }
}
