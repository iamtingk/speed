package com.test.tingk.tingkproject.data.model;

import java.io.Serializable;

/**
 * Created by tingk on 2017/11/23.
 */

public class RecordModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String CityName;
    private String RegionName;
    private String Address;
    private String DeptNm;
    private String BranchNm;
    private String Longitude;
    private String Latitude;
    private String direct;
    private String limit;

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDeptNm() {
        return DeptNm;
    }

    public void setDeptNm(String deptNm) {
        DeptNm = deptNm;
    }

    public String getBranchNm() {
        return BranchNm;
    }

    public void setBranchNm(String branchNm) {
        BranchNm = branchNm;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
