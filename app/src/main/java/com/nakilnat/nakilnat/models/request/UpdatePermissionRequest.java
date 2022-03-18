package com.nakilnat.nakilnat.models.request;

public class UpdatePermissionRequest {
    private String token;
    private String izin_mail_kampanya;
    private String izin_mail_bildirim;
    private String izin_mail_bulten;
    private String izin_sms_kampanya;
    private String izin_sms_bildirim;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIzin_mail_kampanya() {
        return izin_mail_kampanya;
    }

    public void setIzin_mail_kampanya(String izin_mail_kampanya) {
        this.izin_mail_kampanya = izin_mail_kampanya;
    }

    public String getIzin_mail_bildirim() {
        return izin_mail_bildirim;
    }

    public void setIzin_mail_bildirim(String izin_mail_bildirim) {
        this.izin_mail_bildirim = izin_mail_bildirim;
    }

    public String getIzin_mail_bulten() {
        return izin_mail_bulten;
    }

    public void setIzin_mail_bulten(String izin_mail_bulten) {
        this.izin_mail_bulten = izin_mail_bulten;
    }

    public String getIzin_sms_kampanya() {
        return izin_sms_kampanya;
    }

    public void setIzin_sms_kampanya(String izin_sms_kampanya) {
        this.izin_sms_kampanya = izin_sms_kampanya;
    }

    public String getIzin_sms_bildirim() {
        return izin_sms_bildirim;
    }

    public void setIzin_sms_bildirim(String izin_sms_bildirim) {
        this.izin_sms_bildirim = izin_sms_bildirim;
    }
}
