package com.nakilnat.nakilnat.models.request;

public class LoginRequest {
    private String un;
    private String pw;
    private String token;
    private String beniHatirla;

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBeniHatirla() {
        return beniHatirla;
    }

    public void setBeniHatirla(String beniHatirla) {
        this.beniHatirla = beniHatirla;
    }
}
