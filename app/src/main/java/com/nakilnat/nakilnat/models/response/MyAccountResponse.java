package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class MyAccountResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("un")
    private String un;

    @SerializedName("tc")
    private String tc;

    @SerializedName("kayit_tarihi")
    private String kayitTarihi;

    @SerializedName("hesap_turu")
    private String hesapTuru;

    @SerializedName("firma_adi")
    private String firmaAdi;

    @SerializedName("sabit_tel")
    private String sabitTel;

    @SerializedName("cep_tel")
    private String cepTel;

    @SerializedName("acik_adres")
    private String acikAdres;

    @SerializedName("il")
    private String il;

    @SerializedName("ilce")
    private String ilce;

    @SerializedName("firma_vd")
    private String firma_vd;

    @SerializedName("firma_vn")
    private String firma_vn;

    @SerializedName("web")
    private String web;

    @SerializedName("hakkimda")
    private String hakkimda;

    @SerializedName("uye_durum")
    private String uyeDurum;

    @SerializedName("eposta")
    private String permissionEmail;

    @SerializedName("bildirim")
    private String permissionNotification;

    @SerializedName("sms")
    private String permissionSms;

    @SerializedName("telefon")
    private String permissionPhone;

    @SerializedName("yildiz")
    private String accountPoint;

    public MyAccountResponse(String id, String un, String tc, String kayitTarihi, String hesapTuru, String firmaAdi, String sabitTel, String cepTel, String acikAdres, String il, String ilce, String firma_vd, String firma_vn, String web, String hakkimda, String uyeDurum, String permissionEmail, String permissionNotification, String permissionSms, String permissionPhone, String accountPoint) {
        this.id = id;
        this.un = un;
        this.tc = tc;
        this.kayitTarihi = kayitTarihi;
        this.hesapTuru = hesapTuru;
        this.firmaAdi = firmaAdi;
        this.sabitTel = sabitTel;
        this.cepTel = cepTel;
        this.acikAdres = acikAdres;
        this.il = il;
        this.ilce = ilce;
        this.firma_vd = firma_vd;
        this.firma_vn = firma_vn;
        this.web = web;
        this.hakkimda = hakkimda;
        this.uyeDurum = uyeDurum;
        this.permissionEmail = permissionEmail;
        this.permissionNotification = permissionNotification;
        this.permissionSms = permissionSms;
        this.permissionPhone = permissionPhone;
        this.accountPoint = accountPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getKayitTarihi() {
        return kayitTarihi;
    }

    public void setKayitTarihi(String kayitTarihi) {
        this.kayitTarihi = kayitTarihi;
    }

    public String getHesapTuru() {
        return hesapTuru;
    }

    public void setHesapTuru(String hesapTuru) {
        this.hesapTuru = hesapTuru;
    }

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

    public String getSabitTel() {
        return sabitTel;
    }

    public void setSabitTel(String sabitTel) {
        this.sabitTel = sabitTel;
    }

    public String getCepTel() {
        return cepTel;
    }

    public void setCepTel(String cepTel) {
        this.cepTel = cepTel;
    }

    public String getAcikAdres() {
        return acikAdres;
    }

    public void setAcikAdres(String acikAdres) {
        this.acikAdres = acikAdres;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getFirma_vd() {
        return firma_vd;
    }

    public void setFirma_vd(String firma_vd) {
        this.firma_vd = firma_vd;
    }

    public String getFirma_vn() {
        return firma_vn;
    }

    public void setFirma_vn(String firma_vn) {
        this.firma_vn = firma_vn;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getHakkimda() {
        return hakkimda;
    }

    public void setHakkimda(String hakkimda) {
        this.hakkimda = hakkimda;
    }

    public String getUyeDurum() {
        return uyeDurum;
    }

    public void setUyeDurum(String uyeDurum) {
        this.uyeDurum = uyeDurum;
    }

    public String getPermissionEmail() {
        return permissionEmail;
    }

    public void setPermissionEmail(String permissionEmail) {
        this.permissionEmail = permissionEmail;
    }

    public String getPermissionNotification() {
        return permissionNotification;
    }

    public void setPermissionNotification(String permissionNotification) {
        this.permissionNotification = permissionNotification;
    }

    public String getPermissionSms() {
        return permissionSms;
    }

    public void setPermissionSms(String permissionSms) {
        this.permissionSms = permissionSms;
    }

    public String getPermissionPhone() {
        return permissionPhone;
    }

    public void setPermissionPhone(String permissionPhone) {
        this.permissionPhone = permissionPhone;
    }

    public String getAccountPoint() {
        return accountPoint;
    }

    public void setAccountPoint(String accountPoint) {
        this.accountPoint = accountPoint;
    }
}
