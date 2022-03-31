package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class MyReviewsResponse {
    @SerializedName("yorumYapan")
    private String reviewUser;

    @SerializedName("yorum")
    private String reviewDescription;

    @SerializedName("tarih")
    private String reviewTime;

    @SerializedName("puan")
    private String reviewPoint;

    public String getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(String reviewUser) {
        this.reviewUser = reviewUser;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(String reviewPoint) {
        this.reviewPoint = reviewPoint;
    }
}
