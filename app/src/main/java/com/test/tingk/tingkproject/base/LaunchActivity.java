package com.test.tingk.tingkproject.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtntech.android.lib.support.activity.TBaseActivity;
import com.test.tingk.tingkproject.R;
import com.test.tingk.tingkproject.config.AppConfig;
import com.test.tingk.tingkproject.data.DataHolder;
import com.test.tingk.tingkproject.data.model.RecordModel;
import com.test.tingk.tingkproject.data.model.ResponseModel;
import com.test.tingk.tingkproject.data.model.ResultModel;
import com.test.tingk.tingkproject.helper.SPHelper;
import com.test.tingk.tingkproject.module.list.ShowListFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LaunchActivity extends TBaseActivity {
    private SPHelper spHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        spHelper = new SPHelper(getApplicationContext());

        new TransTask().execute(AppConfig.JSON_URL);
    }

    class TransTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line = in.readLine();
                while (line != null) {
                    sb.append(line);
                    line = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            spHelper.editSP(AppConfig.JSON_SP_NAME, s);
            Log.e("readSP", spHelper.readSP(AppConfig.JSON_SP_NAME, ""));

            DataHolder.getInstance().setData("record_items",getRecordData());
            goContainerPage(DrawerMenuActivity.class, ShowListFragment.class.getName(), null, true, PAGE_DEFAULT_ANIMATION);

        }
    }


    private List<RecordModel> getRecordData() {
        //解析開始
        SPHelper spHelper = new SPHelper(getApplicationContext());
        Gson gson = new GsonBuilder().create();
        String json = spHelper.readSP(AppConfig.JSON_SP_NAME, "");
        ResponseModel responseModel = gson.fromJson(json, ResponseModel.class);
        ResultModel resultModel = gson.fromJson(gson.toJson(responseModel.getResult()), ResultModel.class);
        List<RecordModel> list_items = resultModel.getRecords();
        list_items.remove(0);//第一筆試測試資料，所以刪除
        //解析結束
        return list_items;

    }


}


