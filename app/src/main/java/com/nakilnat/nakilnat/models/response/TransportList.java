package com.nakilnat.nakilnat.models.response;

public class TransportList {
    private String sourceCity;
    private String DestinyCity;
    private String money;
    private String thing;
    private String statu;

    public TransportList(String sourceCity, String destinyCity, String money, String thing, String statu) {
        this.sourceCity = sourceCity;
        DestinyCity = destinyCity;
        this.money = money;
        this.thing = thing;
        this.statu = statu;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestinyCity() {
        return DestinyCity;
    }

    public void setDestinyCity(String destinyCity) {
        DestinyCity = destinyCity;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }
}
