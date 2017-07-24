package com.tour.tourapp.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import com.tour.tourapp.App;
import com.tour.tourapp.R;

/**
 * Created by ATRSnail on 2017/7/20.
 */

public class InitUtils {

    /**
     * 初始化 SwipeRefreshLayout
     * @param swipe_refresh
     * @param listener   刷新监听
     */
    public static void initSwipRefresh(SwipeRefreshLayout swipe_refresh, SwipeRefreshLayout.OnRefreshListener listener) {
        swipe_refresh.setOnRefreshListener(listener);
        swipe_refresh.setColorSchemeColors(App.getAppContext().getResources().getIntArray(R.array.gplus_colors));
    }
}
