package com.tour.tourapp.entity;

import com.amap.api.maps.model.LatLng;

/**
 * Created by yiyi.qi on 16/10/10.
 * 地区点
 */

public class RegionItem implements ClusterItem {
    private LatLng mLatLng;//地球上一个地点。
    private String mTitle;//商铺名称

    public RegionItem(LatLng latLng, String title) {
        mLatLng=latLng;
        mTitle=title;
    }

    @Override
    public LatLng getPosition() {
        // TODO Auto-generated method stub
        return mLatLng;
    }

    public String getTitle(){
        return mTitle;
    }

    @Override
    public String toString() {
        return "RegionItem{" +
                "mLatLng=" + mLatLng +
                ", mTitle='" + mTitle + '\'' +
                '}';
    }
}
