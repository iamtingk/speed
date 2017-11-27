package com.test.tingk.tingkproject.module.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

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
import com.test.tingk.tingkproject.util.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tingk on 2017/11/25.
 */

public class ShowListFragment extends BaseFragment {
    private View view;

    //元件
    private Spinner spinnerCity;
    private Spinner spinnerArea;
    private RecyclerView recyclerView;

    //Adapter
    private SpinnerAdapter spinnerCityAdapter;
    private SpinnerAdapter spinnerAreaAdapter;
    private RecyclerAdapter recyclerAdapter;

    //儲存測速開放資料
    private List<RecordModel> record_items;// 全部測速開放資料
    private List<RecordModel> record_select_items;// 暫存符合條件的測速開放資料

    //儲存縣市地區
    private String[] cityArray;// 縣市
    private List<AreaModel> areadata;// 地區

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_showlist, container, false);
        initView();
        initParam();
        initListener();
        return view;
    }

    @Override
    protected void initView() {
        spinnerCity = (Spinner) view.findViewById(R.id.spinner_city);
        spinnerArea = (Spinner) view.findViewById(R.id.spinner_area);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_showlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemOffsetDecoration itemDecoration =
                new ItemOffsetDecoration(getContext(), R.dimen.grid_item_offset);
        recyclerView.addItemDecoration(itemDecoration);
        getActivity().setTitle("顯示列表");



    }

    @Override
    protected void initParam() {
        // 測速開放資料
        record_items = DataHolder.getInstance().getData(AppConfig.DATA_KEY);

        // 全部縣市
        cityArray = AppConfig.ARRAY_TW_CITY;

        // 全部地區
        Gson gson = new GsonBuilder().create();
        CityModel cityData = gson.fromJson(AppConfig.JSON_TW,CityModel.class);
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
                    if (cityArray[position].equals(areadata.get(i).getCityName())){
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
                // 格式化
                record_select_items = new ArrayList<RecordModel>();

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
                    // 0 -> 台北市，getAreaList()[1] -> 大同區，當前在大同區
                    // areadata.get(0).getAreaList()[position])

                }
                recyclerAdapter = new RecyclerAdapter(baseActivity, record_select_items);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}