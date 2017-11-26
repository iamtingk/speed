package com.test.tingk.tingktest.util.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by tingk on 2017/11/27.
 */

public class HttpConnector {
    private static Context context;
    public HttpConnector(Context context)
    {
        this.context = context;
    }

    // 檢查目前是否有網路連線
    public static boolean isNetworkAvailable(Context context)
    {
        boolean result = false;

        ConnectivityManager cm = (ConnectivityManager)context.
                getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm == null){
            result = false;
        }else{

            NetworkInfo info = cm.getActiveNetworkInfo();

            if (info != null && info.isConnected()){
                result = true;
            }else{
                result = false;
            }
        }

        return result;
    }
}
