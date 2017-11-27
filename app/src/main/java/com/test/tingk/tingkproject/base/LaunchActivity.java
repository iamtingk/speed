package com.test.tingk.tingkproject.base;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class LaunchActivity extends com.test.tingk.tingktest.activity.TBaseActivity {
    private SPHelper spHelper;
    private TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        txtStatus = (TextView)findViewById(R.id.txt_status);
        spHelper = new SPHelper(getApplicationContext());

        new TransTask().execute(AppConfig.JSON_URL);
    }

    class TransTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            txtStatus.setText("下載資料中...");
            super.onPreExecute();
        }

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
        list_items.remove(0);//第一筆是測試資料，所以刪除
        txtStatus.setText("完成...");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //解析結束
        return list_items;

    }


}


