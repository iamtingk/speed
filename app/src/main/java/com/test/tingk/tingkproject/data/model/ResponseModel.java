package com.test.tingk.tingkproject.data.model;

import java.io.Serializable;

/**
 * Created by tingk on 2017/11/23.
 */
public class ResponseModel implements Serializable {

    private boolean success;
    private ResultModel result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultModel getResult() {
        return result;
    }
    public void setResult(ResultModel result) {
        this.result = result;
    }

}
