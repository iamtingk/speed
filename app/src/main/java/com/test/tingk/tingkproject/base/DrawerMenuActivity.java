package com.test.tingk.tingkproject.base;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mtntech.android.lib.support.activity.TDrawerMenuActivity;
import com.test.tingk.tingkproject.R;
import com.test.tingk.tingkproject.data.adapter.MenuListAdapter;
import com.test.tingk.tingkproject.module.about.AboutFragment;
import com.test.tingk.tingkproject.module.list.ShowListFragment;
import com.test.tingk.tingkproject.module.map.ShowMapFragment;
import com.test.tingk.tingkproject.module.set.SettingFragment;

public class DrawerMenuActivity extends TDrawerMenuActivity {
    private MenuListAdapter adapter;
    private String[] defaultMenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        initDrawerList();
        setMenuItems();
        initParam();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /* -----------------------------------------------------------
     * Init Process
     * --------------------------------------------------------- */

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(
                    new ColorDrawable(getResources().getColor(R.color.colorSpeedLightSkyBlue)));
        }
    }

    private void initDrawerList() {
        // 設定選單背景色
        drawerList.setBackgroundColor(getResources().getColor(R.color.colorSpeedBlack));

        // 取得選單項目
        defaultMenuItems = this.getResources().getStringArray(R.array.drawer_menu);

        // 設定側選單項目Click事件
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                showPage(position);
            }
        });
    }

    private void initParam() {}


    /**
     * 設定選單項目
     */
    private void setMenuItems() {
        // 設定選單Adapter
        adapter = new MenuListAdapter(this, defaultMenuItems);
        drawerList.setAdapter(adapter);
    }

    /**
     * 顯示畫面
     */
    public void showPage(int index) {

        // 設定Fragment
        String fragmentName = null;
        switch (index) {
            case 0: {
                // 顯示列表
                fragmentName = ShowListFragment.class.getName();
                break;
            }
            case 1: {
                // 顯示地圖
                fragmentName = ShowMapFragment.class.getName();
                break;
            }
            case 2: {
                // 顯示關於測速
                fragmentName = AboutFragment.class.getName();
                break;
            }
            case 3: {
                // 顯示設定
                fragmentName = SettingFragment.class.getName();
                break;
            }
        }

        // 顯示頁面
        goContainerPage(DrawerMenuActivity.class, fragmentName, null, true, PAGE_DEFAULT_ANIMATION);
    }


}