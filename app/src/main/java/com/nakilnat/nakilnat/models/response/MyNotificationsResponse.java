package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class MyNotificationsResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("baslik")
    private String notificationHeader;

    @SerializedName("yazi")
    private String notificationDescription;

    @SerializedName("tarih")
    private String notificationTime;

    @SerializedName("ilanID")
    private String adId;

    @SerializedName("teklifID")
    private String offerId;

    @SerializedName("durum")
    private String notificationState;

    @SerializedName("saat")
    private String notificationHour;

    @SerializedName("link")
    private String notificationLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotificationHeader() {
        return notificationHeader;
    }

    public void setNotificationHeader(String notificationHeader) {
        this.notificationHeader = notificationHeader;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getNotificationState() {
        return notificationState;
    }

    public void setNotificationState(String notificationState) {
        this.notificationState = notificationState;
    }

    public String getNotificationHour() {
        return notificationHour;
    }

    public void setNotificationHour(String notificationHour) {
        this.notificationHour = notificationHour;
    }

    public String getNotificationLink() {
        return notificationLink;
    }

    public void setNotificationLink(String notificationLink) {
        this.notificationLink = notificationLink;
    }
}
