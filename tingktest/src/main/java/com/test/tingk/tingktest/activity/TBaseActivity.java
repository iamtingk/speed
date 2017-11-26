package com.test.tingk.tingktest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.test.tingk.tingktest.util.network.HttpConnector;

/**
 * Created by tingk on 2017/11/27.
 */

public class TBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /* -----------------------------------------------------------
     * Page Transition
     * --------------------------------------------------------- */

    public static final String INTENT_BUNDLE_KEY = "intent_bundle_key";

    public static final int PAGE_NO_ANIMATION = 3001;
    public static final int PAGE_DEFAULT_ANIMATION = 3002;
    public static final int PAGE_FADE_ANIMATION = 3003;

    /**
     * 轉換頁面
     */
    public void goPage(Class cls, Bundle bundle, boolean isFinish, int animation, int[] flags) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtra(INTENT_BUNDLE_KEY, bundle);
        }
        if (animation == PAGE_NO_ANIMATION) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        if ((flags != null) && (flags.length > 0)) {
            for (int value : flags) {
                intent.setFlags(value);
            }
        }
        startActivity(intent);

        if (animation == PAGE_FADE_ANIMATION)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        if (isFinish) finish();
    }

    public void goContainerPage(Class containerClazz,
                                String fragmentName,
                                Bundle fragmentArgs,
                                boolean isFinish,
                                int animation) {
        goContainerPage(containerClazz, fragmentName, fragmentArgs, isFinish, animation, null);
    }

    public void goContainerPage(Class containerClazz,
                                String fragmentName,
                                Bundle fragmentArgs,
                                boolean isFinish,
                                int animation,
                                int[] flags) {
        Bundle bundle = new Bundle();
        bundle.putString(TContainerActivity.CONTENT_FRAGMENT_KEY, fragmentName);
        if (fragmentArgs != null) {
            bundle.putBundle(TContainerActivity.CONTENT_FRAGMENT_ARGS_KEY, fragmentArgs);
        }
        goPage(containerClazz, bundle, isFinish, animation, flags);
    }


    /*=======================================================
      Network Checking
     =======================================================*/

    /**
     * 檢查網路是否連線
     */
    public boolean isNetworkExist() {
        if (!HttpConnector.isNetworkAvailable(this)) {
            return false;
        }
        return true;
    }
}
