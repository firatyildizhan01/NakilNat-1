package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    public LoginResponse(String result) {
        this.result = result;
    }

    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
