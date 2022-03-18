package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class MyInvoiceResponse {

    @SerializedName("id")
    private String id;

    @SerializedName("unvan")
    private String unvan;

    @SerializedName("adres")
    private String adres;

    @SerializedName("vergidaire")
    private String vergiDaire;

    @SerializedName("vergino")
    private String vergiNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getVergiDaire() {
        return vergiDaire;
    }

    public void setVergiDaire(String vergiDaire) {
        this.vergiDaire = vergiDaire;
    }

    public String getVergiNo() {
        return vergiNo;
    }

    public void setVergiNo(String vergiNo) {
        this.vergiNo = vergiNo;
    }
}
