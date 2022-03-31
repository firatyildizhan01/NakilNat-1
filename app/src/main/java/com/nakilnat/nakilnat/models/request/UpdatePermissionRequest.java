package com.nakilnat.nakilnat.models.request;

public class UpdatePermissionRequest {
    private String token;
    private String eposta;
    private String sms;
    private String bildirim;
    private String telefon;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getBildirim() {
        return bildirim;
    }

    public void setBildirim(String bildirim) {
        this.bildirim = bildirim;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
