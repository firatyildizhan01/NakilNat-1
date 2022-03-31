package com.nakilnat.nakilnat.models.request;


public class GetDistrictRequest {
    private String ilid;

    public GetDistrictRequest(String ilid) {
        this.ilid = ilid;
    }

    public String getIlid() {
        return ilid;
    }

    public void setIlid(String ilid) {
        this.ilid = ilid;
    }
}
