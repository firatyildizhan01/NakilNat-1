package com.nakilnat.nakilnat.models;

import com.google.gson.annotations.SerializedName;

public class IlceResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("ad")
    private String ad;

    @SerializedName("il_id")
    private String ilId;

    public IlceResponse(String id, String ad, String ilId) {
        this.id = id;
        this.ad = ad;
        this.ilId = ilId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getIlId() {
        return ilId;
    }

    public void setIlId(String ilId) {
        this.ilId = ilId;
    }
}
