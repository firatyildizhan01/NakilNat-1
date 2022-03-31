package com.nakilnat.nakilnat.models.response;

public class GetProvinceResponse {
    public GetProvinceResponse(String id, String ad) {
        this.id = id;
        this.ad = ad;
    }

    private String id;
    private String ad;

    public String getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }
}
