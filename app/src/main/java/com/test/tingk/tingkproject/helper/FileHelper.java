package com.test.tingk.tingkproject.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.mtntech.android.lib.support.util.network.HttpConnector;
import com.test.tingk.tingkproject.config.AppConfig;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by tingk on 2017/11/24.
 */

public class FileHelper {

    /**
     * 下載Json
     */
    public static void downLoadjson(final Context context) {
        final HttpConnector connector = new HttpConnector(context);
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    //訪問API json
                    Log.e("下載","過程-1");
                    String jsonURL = "http://od.moi.gov.tw/api/v1/rest/datastore/A01010000C-000674-011";
                    JSONParser jsonParser = new JSONParser();
//                    JSONObject jsonObject = (JSONObject) jsonParser.parse(connector.doGet(jsonURL));
                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append((jsonObject));
                    Log.e("下載","過程-2");
                    return stringBuilder.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }
            @Override
            protected void onPostExecute(String str) {
                super.onPostExecute(str);
                updateJson(context, str);

            }
        }.execute();
    }



    /**
     * 檢查json是否存在
     */
    public static boolean isJsonExist(Context context) {
        //檢查文件是否存在
        File file = new File(context.getFilesDir(), AppConfig.JSON_FILE_NAME);
        if (file.exists()) {
            Log.e("檢查檔案", "json file存在");
            return true;
        } else {
            Log.e("檢查檔案", "json file不存在");
            return false;
        }
    }



    /**
     * 更新Json檔案
     */
    public static void updateJson(Context context, String json_into) {

        try {
            FileOutputStream fos = context.openFileOutput(AppConfig.JSON_FILE_NAME, Context.MODE_PRIVATE);
            fos.write(json_into.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 刪除Json檔案
     */
    public static void deleteJson(Context context) {
        File file = new File(context.getFilesDir(), AppConfig.JSON_FILE_NAME);
        file.delete();

    }
}
