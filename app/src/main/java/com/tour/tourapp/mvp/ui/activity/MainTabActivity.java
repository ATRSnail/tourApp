package com.tour.tourapp.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tour.tourapp.R;
import com.tour.tourapp.mvp.ui.fragment.CateFragment;
import com.tour.tourapp.mvp.ui.fragment.MainFragment;
import com.tour.tourapp.mvp.ui.fragment.MemberFragment;
import com.tour.tourapp.mvp.ui.fragment.ShopCarFragment;
import com.tour.tourapp.utils.UT;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 主页面
 */
public class MainTabActivity extends CheckPermissionsActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String FRAGMENT_TAGS = "fragmentTags";
    private static final String CURR_INDEX = "currIndex";
    private static int currIndex = 0;

    private RadioGroup group;

    private ArrayList<String> fragmentTags;
    private FragmentManager fragmentManager;
    private long mExitTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_tab;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        fragmentManager = getSupportFragmentManager();
        mToolbar.setNavigationIcon(null);//去掉Toobar左边箭头
        mToolbar.setNavigationOnClickListener(null);//去掉Toolbar点击事件
        if (savedInstanceState == null) {
            initData();
            initView();
        } else {
            initFromSavedInstantsState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURR_INDEX, currIndex);
        outState.putStringArrayList(FRAGMENT_TAGS, fragmentTags);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        initFromSavedInstantsState(savedInstanceState);
    }

    private void initFromSavedInstantsState(Bundle savedInstanceState) {
        currIndex = savedInstanceState.getInt(CURR_INDEX);
        fragmentTags = savedInstanceState.getStringArrayList(FRAGMENT_TAGS);
        showFragment();
    }


    private void initData() {
        currIndex = 0;
        fragmentTags = new ArrayList<>(Arrays.asList("HomeFragment", "ImFragment", "InterestFragment", "MemberFragment"));
        chageIndex(0);
    }

    private void initView() {
        group = (RadioGroup) findViewById(R.id.group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.foot_bar_home:
                        chageIndex(0);
                        break;
                    case R.id.foot_bar_im:
                        chageIndex(1);
                        break;
                    case R.id.foot_bar_interest:
                        chageIndex(2);
                        break;
                    case R.id.main_footbar_user:
                        chageIndex(3);
                        break;
                    default:
                        break;
                }

            }
        });

        showFragment();
    }

    private void chageIndex(int index) {
        String title="";

        switch (index) {
            case 0:
               hideToolBar();
                break;
            case 1:
                hideToolBar();
                break;
            case 2:
                if (mToolbar != null)
                    mToolbar.setVisibility(View.VISIBLE);
                title= "购物车";
                break;
            case 3:
                hideToolBar();
                break;
        }
        setCustomTitle(title);
        currIndex = index;
        showFragment();
    }

    private void showFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTags.get(currIndex));
        if (fragment == null) {
            fragment = instantFragment(currIndex);
        }
        for (int i = 0; i < fragmentTags.size(); i++) {
            Fragment f = fragmentManager.findFragmentByTag(fragmentTags.get(i));
            if (f != null && f.isAdded()) {
                fragmentTransaction.hide(f);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTags.get(currIndex));
        }
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    private Fragment instantFragment(int currIndex) {
        switch (currIndex) {
//            case 0:return new MainFragment();
            case 0:return new MemberFragment();
            case 1:
                return new CateFragment();
            case 2:
                return new ShopCarFragment();
            case 3:
                return new MemberFragment();
            default:
                return null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                // 如果两次按键时间间隔大于2000毫秒，则不退出
                UT.showNormal( getResources().getString(R.string.second_back_hint));
                 mExitTime = System.currentTimeMillis();//更新mExitTime
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
