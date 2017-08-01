package com.tour.tourapp.utils;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.tour.tourapp.R;

/**
 * Created by ATRSnail on 2017/7/27.
 * 作者：Carson_Ho
 * 链接：http://www.jianshu.com/p/3682f6536e49
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

public class Search_View extends LinearLayout {

    private static Context context;
    /*UI组件*/
    private TextView tv_clear;
    public static EditText mSearchEdit;
    public static TextView cate_search;
    /*列表及其适配器*/
    public static ListView listView;
    private static  BaseAdapter adapter;
    /*数据库工具*/
    public static DBUtils dbUtils;

    public Search_View(Context context) {
        this(context, null);
    }

    public Search_View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Search_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        initView();
        initSearchBox();
    }

    //初始化UI组件
    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.layout_search, this);
        mSearchEdit = (EditText) findViewById(R.id.edit_search);
        tv_clear = (TextView) findViewById(R.id.clear_search);
        listView = (ListView) findViewById(R.id.lv_search);
        cate_search = (TextView) findViewById(R.id.cate_search);


    }

    /*初始化搜索框*/
    private void initSearchBox() {
        //实例化数据库
        dbUtils = new DBUtils(context);

        // 第一次进入时查询所有的历史记录
        showData(dbUtils.queryData(""));


        //"清空搜索历史"按钮
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //清空数据库
                dbUtils.deleteData();
                showData(dbUtils.queryData(""));
            }
        });

        //搜索框的文本变化实时监听
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //输入后调用该方法
            @Override
            public void afterTextChanged(Editable s) {
                //每次输入后都查询数据库并显示
                //根据输入的值去模糊查询数据库中有没有数据
                String tempName = mSearchEdit.getText().toString();
                showData(dbUtils.queryData(tempName));
            }
        });

    }

    public static void showData(Cursor cursor) {
        // 创建adapter适配器对象,装入模糊搜索的结果,展示搜索历史
        adapter = new SimpleCursorAdapter(context,R.layout.layout_search_item, cursor, new String[]{"name"},
                new int[]{R.id.tv_history}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
