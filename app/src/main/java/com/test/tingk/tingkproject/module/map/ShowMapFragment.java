package com.test.tingk.tingkproject.module.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.tingk.tingkproject.R;
import com.test.tingk.tingkproject.base.BaseFragment;
import com.test.tingk.tingkproject.config.AppConfig;
import com.test.tingk.tingkproject.data.DataHolder;
import com.test.tingk.tingkproject.data.adapter.RecyclerAdapter;
import com.test.tingk.tingkproject.data.adapter.SpinnerAdapter;
import com.test.tingk.tingkproject.data.model.AreaModel;
import com.test.tingk.tingkproject.data.model.CityModel;
import com.test.tingk.tingkproject.data.model.RecordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tingk on 2017/11/25.
 */

public class ShowMapFragment extends BaseFragment implements OnMapReadyCallback {

    //元件
    private GoogleMap gMap;
    private View view;
    private Spinner spinnerCity;
    private Spinner spinnerArea;

    //Adapter
    private SpinnerAdapter spinnerCityAdapter;
    private SpinnerAdapter spinnerAreaAdapter;

    //儲存測速開放資料
    private List<RecordModel> record_items;// 全部測速開放資料
    private List<RecordModel> record_select_items;// 暫存符合條件的測速開放資料

    //儲存縣市地區
    private String[] cityArray;// 縣市
    private List<AreaModel> areadata;// 地區


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_showmap, container, false);
        initView();
        initParam();
        initListener();
        return view;
    }

    @Override
    protected void initView() {
        spinnerCity = (Spinner) view.findViewById(R.id.spinner_city);
        spinnerArea = (Spinner) view.findViewById(R.id.spinner_area);

        // google Map初始化
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getActivity().setTitle(R.string.show_map);

    }

    @Override
    protected void initParam() {
        // 測速開放資料
        record_items = DataHolder.getInstance().getData(AppConfig.DATA_KEY);

        // 全部縣市
        cityArray = AppConfig.ARRAY_TW_CITY;

        // 全部地區
        Gson gson = new GsonBuilder().create();
        CityModel cityData = gson.fromJson(AppConfig.JSON_TW, CityModel.class);
        areadata = cityData.getALLCity();

        // 顯示縣市 -> spinnerCity
        spinnerCityAdapter = new SpinnerAdapter(baseActivity, cityArray, "縣市選擇：");
        spinnerCity.setAdapter(spinnerCityAdapter);
        spinnerCity.setSelection(0);
    }

    @Override
    protected void initListener() {

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                record_select_items = new ArrayList<RecordModel>(); //格式化

                // 比對 - 選擇的spinnerCity的縣市 比對 全部地區內的縣市
                // 獲取 - 該縣市的所有地區
                for (int i = 0; i < areadata.size(); i++) {
                    if (cityArray[position].equals(areadata.get(i).getCityName())) {
                        // 顯示該縣市的所有地區 -> spinnerArea
                        spinnerAreaAdapter = new SpinnerAdapter(baseActivity, areadata.get(i).getAreaList(), "地區選擇：");
                        spinnerArea.setAdapter(spinnerAreaAdapter);
                        spinnerArea.setSelection(0);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                record_select_items = new ArrayList<RecordModel>(); // 格式化
                gMap.clear(); // 清除已增加的Marker


                // 比對 - 所有測速開放資料的縣市 比對 spinnerCity所選的縣市
                // 比對 - 所有測速開放資料的地區 比對 spinnerCity所選的地區
                // 獲取 - 符合縣市、地區條件的資料
                for (int i = 0; i < record_items.size(); i++) {
                    if (record_items.get(i).getCityName().equals(spinnerCity.getSelectedItem().toString())) {//
                        if (record_items.get(i).getRegionName().equals(areadata.get(spinnerCity.getSelectedItemPosition()).getAreaList()[position])) {
                            record_select_items.add(record_items.get(i));
                        }
                    }

                    // 備註
                    // areadata.get(0).CityName -> 臺北市
                    // getAreaList()[1] -> 大同區，當前在大同區
                    // areadata.get(0).getAreaList()[position])

                }


                //根據暫存資料，增加Marker並移動到第一個Marker
                for (int i = 0; i < record_select_items.size(); i++) {
                    gMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(record_select_items.get(i).getLatitude()), Double.valueOf(record_select_items.get(i).getLongitude()))).title("測速：" + record_select_items.get(i).getLimit())).showInfoWindow();
                }
                if (!isEmpty(record_select_items)){
                    gMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.valueOf(record_select_items.get(0).getLatitude()), Double.valueOf(record_select_items.get(0).getLongitude()))));
                }else {
                    Toast.makeText(baseActivity, "無測速照相!", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        gMap.getUiSettings().setZoomControlsEnabled(true);  // 右下角的放大縮小功能
        gMap.animateCamera(CameraUpdateFactory.zoomTo(14)); // 放大地圖到 14 倍大


    }
}
