package com.tour.tourapp.entity;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiyi.qi on 16/10/10.
 */

public class Cluster {


    private LatLng mLatLng;
    private List<ClusterItem> mClusterItems;
    private Marker mMarker;
    private String mTitle;


    public Cluster( LatLng latLng,String title) {
        mTitle = title;
        mLatLng = latLng;
        mClusterItems = new ArrayList<ClusterItem>();
    }

    public void addClusterItem(ClusterItem clusterItem) {
        mClusterItems.add(clusterItem);
    }

    public int getClusterCount() {
        return mClusterItems.size();
    }



    public LatLng getCenterLatLng() {
        return mLatLng;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setMarker(Marker marker) {
        mMarker = marker;
    }

    public Marker getMarker() {
        return mMarker;
    }

    public List<ClusterItem> getClusterItems() {
        return mClusterItems;
    }
}
