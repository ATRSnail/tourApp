package com.tour.tourapp.mvp.ui.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.socks.library.KLog;
import com.tour.tourapp.App;
import com.tour.tourapp.R;
import com.tour.tourapp.api.LoadNewsType;
import com.tour.tourapp.api.RetrofitManager;
import com.tour.tourapp.entity.ChildrenBean;
import com.tour.tourapp.entity.GoodsClassify;
import com.tour.tourapp.entity.GoodsDetailBean;
import com.tour.tourapp.entity.Rspclassify;
import com.tour.tourapp.mvp.adapter.GoodsClassifyAdapter;
import com.tour.tourapp.mvp.adapter.ShopGoodAdapter;
import com.tour.tourapp.mvp.presenter.impl.CatePresenterImpl;
import com.tour.tourapp.mvp.ui.activity.GoodDetailActivity;
import com.tour.tourapp.mvp.view.base.CateView;
import com.tour.tourapp.utils.CheckDataIsEmpty;
import com.tour.tourapp.utils.InitUtils;
import com.tour.tourapp.utils.NetUtil;
import com.tour.tourapp.utils.ScreenUtils;
import com.tour.tourapp.utils.TransformUtils;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 分类--碎片
 */
public class CateFragment extends BaseFragment implements BaseQuickAdapter.OnRecyclerViewItemClickListener
        , CateView, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private ShopGoodAdapter shopGoodAdapter;
    private GoodsClassifyAdapter goodsClassifyAdapter;
    private List<GoodsDetailBean> shopGoodList;//商店商品列表
    List<GoodsClassify> goodsClassifyList;//商品分类列表
    List<ChildrenBean> childrenBeenList;//该商品分类下的 子商品
    int parent;


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
    //分类的顶部栏
    @BindView(R.id.cate_top)
    LinearLayout linearLayout;

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        InitUtils.initSwipRefresh(swipe_refresh, this);
        initGridView();
        initPresenter();
        initPopupWindow();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_catefragment;
    }


    /**
     * 初始化initGridView
     */
    private void initGridView() {
        shopGoodList = new ArrayList<>();
        childrenBeenList = new ArrayList<>();
        shopGoodAdapter = new ShopGoodAdapter(mActivity, shopGoodList);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(shopGoodAdapter);
    }


    private void initPresenter() {
        mPresenter = mGoodListPreter;
        mPresenter.attachView(this);
        mPresenter.onCreate();
    }

    private void initPopupWindow() {
        popupView = LayoutInflater.from(mActivity).inflate(R.layout.sidebar, null, false);
        popupWindow = new PopupWindow(popupView, ScreenUtils.getScreenWidth(mActivity) / 5 * 2,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.sidebar_rv);
        initRecyclerView(recyclerView);
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        goodsClassifyList = new ArrayList<>();
        goodsClassifyAdapter = new GoodsClassifyAdapter(R.layout.classify_item, goodsClassifyList);
        RecyclerViewHelper.initRecyclerViewV(mActivity, recyclerView, true, goodsClassifyAdapter, 1,
                getResources().getColor(R.color.colorPrimary));
        goodsClassifyAdapter.setOnRecyclerViewItemClickListener(this);
        recyclerView.setAdapter(goodsClassifyAdapter);
    }


    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
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
        KLog.a("==========" + msg);
        if (NetUtil.isNetworkAvailable(App.getAppContext())) {
            if (msg != null) {
                Toast.makeText(App.getAppContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }

        }
    }


    /**
     * @param shopGoodBeen 商店商品的信息
     * @param loadType
     */
    @Override
    public void setShopGoodList(List<GoodsDetailBean> shopGoodBeen, @LoadNewsType.checker int loadType) {
        switch (loadType) {
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                swipe_refresh.setRefreshing(false);
                shopGoodList = shopGoodBeen;
                shopGoodAdapter.setNewData(shopGoodList);
                break;
            case LoadNewsType.TYPE_REFRESH_ERROR:
                swipe_refresh.setRefreshing(false);
                break;
        }
    }

    /**
     * @param goodsClassify 商品的分类状态列表
     * @param loadType
     */
    @Override
    public void setGoodsClassifyList(List<GoodsClassify> goodsClassify, @LoadNewsType.checker int loadType) {
        switch (loadType) {
            case LoadNewsType.TYPE_REFRESH_SUCCESS:
                goodsClassifyList = goodsClassify;
                goodsClassifyAdapter.setNewData(goodsClassifyList);
                break;

            case LoadNewsType.TYPE_REFRESH_ERROR:
                break;
        }
    }

    //点击侧滑栏事件
    @Override
    public void onItemClick(View view, int position) {
        goodsClassifyAdapter.setOnClickPostion(position);
        if (!CheckDataIsEmpty.checkList(goodsClassifyList)) {
            childrenBeenList = goodsClassifyList.get(position).getChildren();
            if (!CheckDataIsEmpty.checkList(childrenBeenList)) {
                parent = childrenBeenList.get(0).getParent();
                //按类型查找商品 并且选择商品的排列循序
                findGoodsList(parent);
            }
        }


    }

    private void findGoodsList(int parent) {
        RetrofitManager.getInstance(1).classify_First("","0",String.valueOf(parent))
                .compose(TransformUtils.<Rspclassify>defaultSchedulers())
                .subscribe(new Subscriber<Rspclassify>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.toString());
                    }

                    @Override
                    public void onNext(Rspclassify rspclassify) {
                        KLog.d(rspclassify.toString());
                        shopGoodAdapter.setNewData(rspclassify.getBody().getShopGoods());
                    }
                });
    }

    //点击商品事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!CheckDataIsEmpty.checkList(shopGoodList)) {
            GoodDetailActivity.launch(mActivity, shopGoodList.get(position).getId());
        }
    }
}
