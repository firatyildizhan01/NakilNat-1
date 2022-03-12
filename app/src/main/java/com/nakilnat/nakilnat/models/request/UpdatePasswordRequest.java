package com.nakilnat.nakilnat.models.request;

public class UpdatePasswordRequest {
    private String token;
    private String eskiSifre;
    private String yeniSifre;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEskiSifre() {
        return eskiSifre;
    }

    public void setEskiSifre(String eskiSifre) {
        this.eskiSifre = eskiSifre;
    }

    public String getYeniSifre() {
        return yeniSifre;
    }

    public void setYeniSifre(String yeniSifre) {
        this.yeniSifre = yeniSifre;
    }
}
