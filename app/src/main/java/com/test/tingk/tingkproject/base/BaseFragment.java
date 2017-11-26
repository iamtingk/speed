package com.test.tingk.tingkproject.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.test.tingk.tingktest.activity.TBaseActivity;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Created by tingk on 2017/11/25.
 */

public abstract class BaseFragment extends Fragment {
    protected abstract void initView();
    protected abstract void initParam();
    protected abstract void initListener();
    protected TBaseActivity baseActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = (TBaseActivity) getActivity();
    }

    /**
     * 判斷物件是否空
     */
    public boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        // else
        return false;
    }


    private void setLog(String cla,String info){
        if(info.length() > 4000) {
            for(int i=0;i<info.length();i+=4000){
                if(i+4000<info.length())
                    Log.e(cla+i,info.substring(i, i+4000));
                else
                    Log.e(cla+i,info.substring(i, info.length()));
            }
        } else
            Log.e("resinfo",info);
    }

}
