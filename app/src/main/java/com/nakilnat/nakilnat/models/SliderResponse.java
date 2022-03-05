package com.nakilnat.nakilnat.models;

import com.google.gson.annotations.SerializedName;

public class SliderResponse {
    public SliderResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("id")
    private String id;
}