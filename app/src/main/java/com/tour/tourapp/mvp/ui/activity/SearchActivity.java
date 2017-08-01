package com.tour.tourapp.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tour.tourapp.R;
import com.tour.tourapp.utils.CheckDataIsEmpty;
import com.tour.tourapp.utils.UT;

import javax.inject.Inject;

import static com.tour.tourapp.utils.Search_View.*;

/**
 * 首页--搜索页面
 */
public class SearchActivity extends BaseActivity implements OnClickListener, AdapterView.OnItemClickListener
        , TextView.OnEditorActionListener {


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
        mSearchEdit.setOnEditorActionListener(this);
        cate_search.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }


    public void onSearchFinish(View v) {
        switch (v.getId()) {
            case R.id.back_search:
                finish();
            case R.id.cancel_search:
                finish();
                break;
        }
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
                cate_search.setText("店铺");
                popupWindow.dismiss();
            }
        });
        tv_good_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cate_search.setText("商品");
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 按钮的监听
     */
    public void openPopWindow() {
        //从底部显示
        popupWindow.showAsDropDown(cate_search, 0, 10);
    }

    @Override
    public void onClick(View v) {
        openPopWindow();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //获取到用户点击列表里的文字,并自动填充到搜索框内
        TextView textView = (TextView) view.findViewById(R.id.tv_history);
        String name = textView.getText().toString();
        mSearchEdit.setText(name);

        // 当用户点击搜索历史里的字段后,立即查询关键字
        jump();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH ) {
            jump();
        }
        return false;
    }

    private void jump() {
        etContent = mSearchEdit.getText().toString().trim();
        if (CheckDataIsEmpty.checkString(etContent)) {
            UT.show("请输入关键字");
            return ;
        }

        // 隐藏键盘
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
        boolean hasData = dbUtils.hasData(etContent);
        if (!hasData) {
            dbUtils.insertData(etContent);
        }
        if (cate_search.getText().equals("店铺")) {
            shopsName = etContent;
            goodsName = "";
        } else {
            shopsName = "";
            goodsName = etContent;
        }
        SearchResultActivity.launch(SearchActivity.this, shopsName, goodsName);
    }
}
