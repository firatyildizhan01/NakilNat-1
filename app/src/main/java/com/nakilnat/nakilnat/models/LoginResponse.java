package com.nakilnat.nakilnat.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("UserName")
    private String userName;

    @SerializedName("Password")
    private String password;

    public String getUserName() {
        return userName;
    }

    public LoginResponse(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
