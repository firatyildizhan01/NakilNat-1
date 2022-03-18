package com.nakilnat.nakilnat.models.request;

public class UpdateAccountRequest {
    private String email;
    private String sifre;
    private String firma_adi;
    private String sabit_tel;
    private String acik_adres;
    private String il;
    private String ilce;
    private String firma_vn;
    private String firma_vd;
    private String web;
    private String hakkimda;
    private String hesap_turu;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getFirma_adi() {
        return firma_adi;
    }

    public void setFirma_adi(String firma_adi) {
        this.firma_adi = firma_adi;
    }

    public String getSabit_tel() {
        return sabit_tel;
    }

    public void setSabit_tel(String sabit_tel) {
        this.sabit_tel = sabit_tel;
    }

    public String getAcik_adres() {
        return acik_adres;
    }

    public void setAcik_adres(String acik_adres) {
        this.acik_adres = acik_adres;
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

    public String getFirma_vn() {
        return firma_vn;
    }

    public void setFirma_vn(String firma_vn) {
        this.firma_vn = firma_vn;
    }

    public String getFirma_vd() {
        return firma_vd;
    }

    public void setFirma_vd(String firma_vd) {
        this.firma_vd = firma_vd;
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

    public String getHesap_turu() {
        return hesap_turu;
    }

    public void setHesap_turu(String hesap_turu) {
        this.hesap_turu = hesap_turu;
    }
}
