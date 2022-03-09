package com.nakilnat.nakilnat.models.request;

public class NewPasswordRequest {
    private String dogrulama;
    private String sifre;

    public String getDogrulama() {
        return dogrulama;
    }

    public void setDogrulama(String dogrulama) {
        this.dogrulama = dogrulama;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
