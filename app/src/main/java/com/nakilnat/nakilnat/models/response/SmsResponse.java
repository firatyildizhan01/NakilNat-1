package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class SmsResponse {

    @SerializedName("result")
    private String result;

    @SerializedName("userid")
    private String userId;

    public SmsResponse(String result, String userId) {
        this.result = result;
        this.userId = userId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
