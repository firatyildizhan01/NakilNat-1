package com.nakilnat.nakilnat.models.response;

public class Reviews {
    private String description;
    private String date;
    private String nameSurname;

    public Reviews(String description, String date, String nameSurname) {
        this.description = description;
        this.date = date;
        this.nameSurname = nameSurname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }
}
