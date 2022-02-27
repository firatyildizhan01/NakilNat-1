package com.nakilnat.nakilnat.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("0")
    private String sifir;

    @SerializedName("sira")
    private String sira;

    @SerializedName("1")
    private String bir;

    @SerializedName("kat")
    private String kat;

    @SerializedName("2")
    private String iki;

    @SerializedName("sayfa")
    private String sayfa;

    @SerializedName("3")
    private String uc;

    @SerializedName("sayfa_link")
    private String sayfaLink;

    @SerializedName("4")
    private String dort;

    @SerializedName("sayfa_ismi")
    private String sayfaIsmi;

    @SerializedName("5")
    private String bes;

    @SerializedName("yazisi")
    private String yazisi;

    @SerializedName("6")
    private String alti;

    public DefaultResponse(String id, String sifir, String sira, String bir, String kat, String iki, String sayfa, String uc, String sayfaLink, String dort, String sayfaIsmi, String bes, String yazisi, String alti) {
        this.id = id;
        this.sifir = sifir;
        this.sira = sira;
        this.bir = bir;
        this.kat = kat;
        this.iki = iki;
        this.sayfa = sayfa;
        this.uc = uc;
        this.sayfaLink = sayfaLink;
        this.dort = dort;
        this.sayfaIsmi = sayfaIsmi;
        this.bes = bes;
        this.yazisi = yazisi;
        this.alti = alti;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSifir() {
        return sifir;
    }

    public void setSifir(String sifir) {
        this.sifir = sifir;
    }

    public String getSira() {
        return sira;
    }

    public void setSira(String sira) {
        this.sira = sira;
    }

    public String getBir() {
        return bir;
    }

    public void setBir(String bir) {
        this.bir = bir;
    }

    public String getKat() {
        return kat;
    }

    public void setKat(String kat) {
        this.kat = kat;
    }

    public String getIki() {
        return iki;
    }

    public void setIki(String iki) {
        this.iki = iki;
    }

    public String getSayfa() {
        return sayfa;
    }

    public void setSayfa(String sayfa) {
        this.sayfa = sayfa;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getSayfaLink() {
        return sayfaLink;
    }

    public void setSayfaLink(String sayfaLink) {
        this.sayfaLink = sayfaLink;
    }

    public String getDort() {
        return dort;
    }

    public void setDort(String dort) {
        this.dort = dort;
    }

    public String getSayfaIsmi() {
        return sayfaIsmi;
    }

    public void setSayfaIsmi(String sayfaIsmi) {
        this.sayfaIsmi = sayfaIsmi;
    }

    public String getBes() {
        return bes;
    }

    public void setBes(String bes) {
        this.bes = bes;
    }

    public String getYazisi() {
        return yazisi;
    }

    public void setYazisi(String yazisi) {
        this.yazisi = yazisi;
    }

    public String getAlti() {
        return alti;
    }

    public void setAlti(String alti) {
        this.alti = alti;
    }
}
