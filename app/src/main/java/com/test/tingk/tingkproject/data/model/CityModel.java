package com.test.tingk.tingkproject.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tingk on 2017/11/23.
 */
public class CityModel implements Serializable {

    private List<AreaModel> ALLCity;

    public List<AreaModel> getALLCity() {
        return ALLCity;
    }

    public void setALLCity(List<AreaModel> ALLCity) {
        this.ALLCity = ALLCity;
    }
}
