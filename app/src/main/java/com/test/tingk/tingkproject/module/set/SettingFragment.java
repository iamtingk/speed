package com.test.tingk.tingkproject.module.set;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.tingk.tingkproject.R;
import com.test.tingk.tingkproject.base.BaseFragment;

/**
 * Created by tingk on 2017/11/25.
 */

public class SettingFragment extends BaseFragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting,container,false);
        initView();
        initParam();
        initListener();
        return view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initParam() {

    }

    @Override
    protected void initListener() {

    }

}