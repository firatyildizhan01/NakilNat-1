package com.nakilnat.nakilnat.models.request;

public class DeleteAccountRequest {
    private String token;
    private String sil;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSil() {
        return sil;
    }

    public void setSil(String sil) {
        this.sil = sil;
    }
}
