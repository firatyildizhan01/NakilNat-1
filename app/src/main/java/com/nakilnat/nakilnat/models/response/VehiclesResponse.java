package com.nakilnat.nakilnat.models.response;

public class VehiclesResponse {


    private String name;

    public VehiclesResponse(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
