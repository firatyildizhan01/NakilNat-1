package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("result")
    private String result;

    public DefaultResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
