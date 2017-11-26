package com.test.tingk.tingkproject.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.test.tingk.tingkproject.config.AppConfig;

/**
 * Created by tingk on 2017/11/24.
 */

public class SPHelper {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    public SPHelper(Context context){
        sp = context.getSharedPreferences(AppConfig.SP_DATA_1, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void editSP(String SPName,String data){
        editor.putString(SPName,data);
        editor.commit();
    }

    public String readSP(String SPName, @Nullable String defValue){
        return  sp.getString(SPName,defValue);

    }

}
