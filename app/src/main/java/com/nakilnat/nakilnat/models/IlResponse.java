package com.nakilnat.nakilnat.models;

import com.google.gson.annotations.SerializedName;

public class IlResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("0")
    private String sifir;

    @SerializedName("ad")
    private String ilName;

    @SerializedName("1")
    private String bir;

    public IlResponse(String id, String sifir, String ilName, String bir) {
        this.id = id;
        this.sifir = sifir;
        this.ilName = ilName;
        this.bir = bir;
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

    public String getIlName() {
        return ilName;
    }

    public void setIlName(String ilName) {
        this.ilName = ilName;
    }

    public String getBir() {
        return bir;
    }

    public void setBir(String bir) {
        this.bir = bir;
    }
}
