package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tour.tourapp.R;
import com.tour.tourapp.utils.UT;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 首页--搜索页面
 */
public class SearchActivity extends BaseActivity {

    @BindView(R.id.btn_search)
    Button btn_search;
    @BindView(R.id.tv_search_cate)
    TextView tv_search_cate;
    @BindView(R.id.et_search_content)
    EditText et_search_content;

    private String shopsName, goodsName;
    private PopupWindow popupWindow;
    private View popView;
    private TextView tv_shop_name, tv_good_name;
    private String etContent;

    @Inject
    Activity mActivity;

    public static void launch(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initViews() {
        initPop();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etContent = et_search_content.getText().toString().trim();
                if (TextUtils.isEmpty(etContent)){
                    UT.show("内容不能为空");
                    return;
                }
                if (tv_search_cate.getText().equals("商铺")){
                    shopsName = etContent;
                    goodsName = "";
                }else {
                    shopsName = "";
                    goodsName = etContent;
                }
                SearchResultActivity.launch(mActivity, shopsName, goodsName);
            }
        });
        tv_search_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopWindow();
            }
        });
    }

    private void initPop() {
        popView = LayoutInflater.from(this).inflate(R.layout.layout_pop_search_cate, null);
        tv_shop_name = (TextView) popView.findViewById(R.id.tv_shop_name);
        tv_good_name = (TextView) popView.findViewById(R.id.tv_good_name);
        //设置弹出框的宽度和高度
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.PopupAnimation);

        tv_shop_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_search_cate.setText("店铺");
                popupWindow.dismiss();
            }
        });
        tv_good_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_search_cate.setText("商品");
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 按钮的监听
     */
    public void openPopWindow() {
        //从底部显示
        popupWindow.showAsDropDown(tv_search_cate, 0, 10);
    }
}
