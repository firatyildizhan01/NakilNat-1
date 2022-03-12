package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class MyAccountResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("un")
    private String un;

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

    @SerializedName("izin_mail_kampanya")
    private String izinMailKampanya;

    @SerializedName("izin_mail_bildirim")
    private String izinMailBildirim;

    @SerializedName("izin_mail_bulten")
    private String izinMailBulten;

    @SerializedName("izin_sms_kampanya")
    private String izinSmsKampanya;

    @SerializedName("izin_sms_bildirim")
    private String izinSmsBildirim;

}
