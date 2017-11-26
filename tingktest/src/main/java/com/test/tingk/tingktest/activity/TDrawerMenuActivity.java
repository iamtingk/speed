package com.test.tingk.tingktest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.test.tingk.tingktest.R;

/**
 * Created by tingk on 2017/11/27.
 */

public class TDrawerMenuActivity extends TContainerActivity {
    protected DrawerLayout drawerLayout;
    protected ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtn_drawer_menu);
        initActionBar();
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return false;
        }
    }

    /* -----------------------------------------------------------
     * Init Process
     * --------------------------------------------------------- */

    private void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void initView() {
        super.initView();

        drawerLayout = (DrawerLayout) findViewById(R.id.layout_drawer);
        drawerList = (ListView) findViewById(R.id.list_drawer);

        // 設定DrawerToggle
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_menu_open, R.string.drawer_menu_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
    }

    /* -----------------------------------------------------------
     * Drawer Actions
     * --------------------------------------------------------- */

    public void openDrawer() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void closeDrawer(int delayTime) {
        // 延遲關閉
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeDrawer();
            }
        }, delayTime);
    }

    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }
}
