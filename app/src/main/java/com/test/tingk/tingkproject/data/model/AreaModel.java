package com.test.tingk.tingkproject.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tingk on 2017/11/23.
 */
public class AreaModel implements Serializable {

    private String CityName;
    private String[] AreaList;

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String[] getAreaList() {
        return AreaList;
    }

    public void setAreaList(String[] areaList) {
        AreaList = areaList;
    }
}
