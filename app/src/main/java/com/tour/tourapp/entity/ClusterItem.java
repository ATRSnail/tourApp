package com.tour.tourapp.entity;

import com.amap.api.maps.model.LatLng;

/**
 * Created by yiyi.qi on 16/10/10.
 * 接口ClusterItem，对应的是聚合点中的每一项
 * 显示在页面上的每一个marker都是一个ClusterItem的实现
 */

public interface ClusterItem {
    /**
     * 返回聚合元素的地理位置
     *
     * @return
     */
     LatLng getPosition();

    String getTitle();
}
