package com.tour.tourapp.mvp.ui.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.socks.library.KLog;
import com.tour.tourapp.App;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.entity.GoodBean;
import com.tour.tourapp.entity.GoodsClassify;
import com.tour.tourapp.entity.ShopGood;
import com.tour.tourapp.mvp.adapter.CustomAdapter;
import com.tour.tourapp.mvp.adapter.GoodsClassifyAdapter;
import com.tour.tourapp.mvp.adapter.ShopGoodAdapter;
import com.tour.tourapp.mvp.presenter.impl.CatePresenterImpl;
import com.tour.tourapp.mvp.presenter.impl.GoodListPresenterImpl;
import com.tour.tourapp.mvp.ui.activity.GoodDetailActivity;
import com.tour.tourapp.mvp.view.base.CateView;
import com.tour.tourapp.mvp.view.base.GoodListView;
import com.tour.tourapp.utils.CustomRecyclerView;
import com.tour.tourapp.utils.NetUtil;
import com.tour.tourapp.utils.ScreenUtils;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分类--碎片
 */
public class CateFragment extends BaseFragment implements BaseQuickAdapter.OnRecyclerViewItemClickListener
        , CateView,SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener {

    private ShopGoodAdapter shopGoodAdapter;
    private GoodsClassifyAdapter goodsClassifyAdapter;
    private List<ShopGood> shopGoods;
    List<GoodsClassify> goodsClassify;

    @BindView(R.id.cate_gv)
    GridView gridView;
    @BindView(R.id.cate_sr)
    SwipeRefreshLayout swipe_refresh;



    @Inject
    Activity mActivity;
    @Inject
    CatePresenterImpl mGoodListPreter;

    private PopupWindow popupWindow;//左侧侧边栏
    private View popupView;
    @BindView(R.id.cate_top)
    LinearLayout linearLayout;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        initSwipeRefreshLayout();
        initGridView();
        initPresenter();
        initPopupWindow();
    }



    @Override
    public int getLayoutId() {
        return R.layout.fragment_catefragment_list;
    }




    /**
     * 初始化initGridView
     */
    private void initGridView() {
        shopGoods = new ArrayList<>();
        shopGoodAdapter = new ShopGoodAdapter(mActivity,shopGoods);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(shopGoodAdapter);
    }


    private void initSwipeRefreshLayout() {
        swipe_refresh.setOnRefreshListener(this);
        swipe_refresh.setColorSchemeColors(getActivity().getResources().getIntArray(R.array.gplus_colors));
    }

    private void initPresenter() {
        mPresenter = mGoodListPreter;
        mPresenter.attachView(this);
        mPresenter.onCreate();
    }

    private void initPopupWindow() {
        popupView = LayoutInflater.from(mActivity).inflate(R.layout.sidebar,null,false);
        popupWindow = new PopupWindow(popupView, ScreenUtils.getScreenWidth(mActivity)/5*2,
                ViewGroup.LayoutParams.MATCH_PARENT,true);
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        RecyclerView recyclerView  = (RecyclerView) popupView.findViewById(R.id.sidebar_rv);
        initRecyclerView(recyclerView);
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        goodsClassify = new ArrayList<>();
        goodsClassifyAdapter = new GoodsClassifyAdapter(R.layout.classify_item,goodsClassify);
        RecyclerViewHelper.initRecyclerViewV(mActivity,recyclerView,true,goodsClassifyAdapter);
        goodsClassifyAdapter.setOnRecyclerViewItemClickListener(this);
        recyclerView.setAdapter(goodsClassifyAdapter);
    }




    @OnClick({R.id.iv_back})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.iv_back :
                openPopWindow();
                break;
        }
    }


    public void openPopWindow() {
            popupWindow.showAsDropDown(linearLayout);
            popupWindow.showAtLocation(popupView, Gravity.LEFT, 0, 0);

    }




    @Override
    public void onRefresh() {
        mGoodListPreter.refreshData();
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void showMsg(String msg) {
        KLog.a("=========="+msg);
        if (NetUtil.isNetworkAvailable(App.getAppContext())){
            if (msg!=null){
                Toast.makeText(App.getAppContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }

        }
    }


    /**
     * @param shopGoods 商品的信息
     * @param loadType
     */
    @Override
    public void setShopGoodList(List<ShopGood> shopGoods, @LoadNewsType.checker int loadType) {
        switch (loadType){
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                swipe_refresh.setRefreshing(false);
                shopGoodAdapter.setNewData(shopGoods);
                shopGoodAdapter.notifyDataSetChanged();
                break;
            case LoadNewsType.TYPE_REFRESH_ERROR:
                swipe_refresh.setRefreshing(false);
                break;
        }
    }

    /**
     *
     * @param goodsClassify  商品的分类状态列表
     * @param loadType
     */
    @Override
    public void setGoodsClassifyList(List<GoodsClassify> goodsClassify, @LoadNewsType.checker int loadType) {
        switch (loadType){
        case LoadNewsType.TYPE_REFRESH_SUCCESS:
        swipe_refresh.setRefreshing(false);
        goodsClassifyAdapter.setNewData(goodsClassify);
        break;
        case LoadNewsType.TYPE_REFRESH_ERROR:
        swipe_refresh.setRefreshing(false);
        break;
    }
    }

    //点击侧滑栏事件
    @Override
    public void onItemClick(View view, int i) {
      goodsClassifyAdapter.setOnClickPostion(i);
     GoodsClassify item=  goodsClassifyAdapter.getData().get(i);
        if (item!=null){
            UT.show(item.getName());
        }
    }

    //点击商品事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodDetailActivity.launch(mActivity, 9);
    }
}
