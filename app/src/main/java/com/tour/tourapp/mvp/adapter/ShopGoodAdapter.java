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
import com.tour.tourapp.R;
import com.tour.tourapp.api.ApiConstants;
import com.tour.tourapp.entity.GoodsDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类---->商店商品的适配器
 */

public class ShopGoodAdapter  extends BaseAdapter {
    List<GoodsDetailBean> data= new ArrayList<>();
    Context context ;
    public ShopGoodAdapter(Context context, List<GoodsDetailBean> data) {
        this.data.addAll(data);
        this.context = context;
    }



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.shopgood,null,false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.good_icon);
            holder.name = (TextView) convertView.findViewById(R.id.good_name);
            holder.price = (TextView) convertView.findViewById(R.id.good_price);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        GoodsDetailBean item = data.get(position);
        holder.name.setText(item.getGoodsName());
        holder.price.setText(item.getPriceS()+"");
        Glide.with(context)
                .load(ApiConstants.NETEAST_HOST + item.getAtt().get(0).getAttUrl())
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .placeholder(R.mipmap.default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.load_fail)
                .into(holder.imageView);
        return convertView;
    }

    public void setNewData(List<GoodsDetailBean> shopGoodBeen) {
        this.data.clear();
        this.data.addAll(shopGoodBeen);
        this.notifyDataSetChanged();
    }

    static  class  ViewHolder{
        TextView name;
        TextView price;
        ImageView imageView;
    }
}
