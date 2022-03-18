package com.nakilnat.nakilnat.models.response;

public class Notification {
    private String notificationSubject;
    private String notificationTime;
    private String notificationDescription;

    public Notification(String notificationSubject, String notificationTime, String notificationDescription) {
        this.notificationSubject = notificationSubject;
        this.notificationTime = notificationTime;
        this.notificationDescription = notificationDescription;
    }

    public String getNotificationSubject() {
        return notificationSubject;
    }

    public void setNotificationSubject(String notificationSubject) {
        this.notificationSubject = notificationSubject;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }
}
