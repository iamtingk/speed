package com.test.tingk.tingktest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.FrameLayout;

import com.test.tingk.tingktest.R;

/**
 * Created by tingk on 2017/11/27.
 */

public class TContainerActivity extends TBaseActivity {
    public static final String CONTENT_FRAGMENT_KEY = "content_fragment_key";
    public static final String CONTENT_FRAGMENT_ARGS_KEY = "content_fragment_args_key";

    protected FrameLayout contentLayout;
    protected Fragment contentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /* -----------------------------------------------------------
     * Init Process
     * --------------------------------------------------------- */

    protected void initView() {
        contentLayout = (FrameLayout) findViewById(R.id.layout_content);
        initContentFragment();
    }

    protected void initContentFragment() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(INTENT_BUNDLE_KEY);
            if (bundle != null) {
                try {
                    // 取得內容Fragment
                    String className = bundle.getString(CONTENT_FRAGMENT_KEY);
                    Class clazz = Class.forName(className);
                    contentFragment = (Fragment) clazz.newInstance();

                    // 取得Fragment參數
                    Bundle args = bundle.getBundle(CONTENT_FRAGMENT_ARGS_KEY);
                    if (args != null) {
                        contentFragment.setArguments(args);
                    }

                    // 顯示內容Fragment
                    setContentFragment(contentFragment,
                            this.getClass().getSimpleName() + "_content_fragment");
                } catch (Exception e) {
                    Log.e("initContentFragment", "Exception:" + e);
                }
            }
        }
    }

    /* -----------------------------------------------------------
     * Content Fragment
     * --------------------------------------------------------- */

    /**
     * 設定內容Fragment
     */
    public void setContentFragment(Fragment fragment, String id) {
        getSupportFragmentManager().beginTransaction().add(
                R.id.layout_content, fragment, id).commit();
    }
}
