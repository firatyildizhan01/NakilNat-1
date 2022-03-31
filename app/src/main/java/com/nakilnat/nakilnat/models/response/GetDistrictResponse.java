package com.nakilnat.nakilnat.models.response;

public class GetDistrictResponse {

    public GetDistrictResponse(String id, String ad, String il_id) {
        this.id = id;
        this.ad = ad;
        this.il_id = il_id;
    }

    private  String id;
    private  String ad;
    private  String il_id;

    public String getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getIl_id() {
        return il_id;
    }
}
