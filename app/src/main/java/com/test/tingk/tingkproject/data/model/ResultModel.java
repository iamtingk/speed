package com.test.tingk.tingkproject.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tingk on 2017/11/23.
 */

public class ResultModel implements Serializable {
    private String resource_id;
    private int limit;
    private int total;
    private List<FieldModel> fields;
    private List<RecordModel> records;

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<FieldModel> getFields() {
        return fields;
    }

    public void setFields(List<FieldModel> fields) {
        this.fields = fields;
    }

    public List<RecordModel> getRecords() {
        return records;
    }

    public void setRecords(List<RecordModel> records) {
        this.records = records;
    }
}
