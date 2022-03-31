package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class MyAdressListResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("uyeID")
    private String userId;

    @SerializedName("baslik")
    private String adressHeader;

    @SerializedName("il")
    private String adressCity;

    @SerializedName("ilce")
    private String adressDistrict;

    @SerializedName("sokak")
    private String adressStreet;

    @SerializedName("mahalle")
    private String adressNeighborhood;

    @SerializedName("bina")
    private String adressBuildingNo;

    @SerializedName("kat")
    private String adressFloor;

    @SerializedName("daire")
    private String adressApertment;

    @SerializedName("adresTarif")
    private String adressDescription;

    @SerializedName("adres")
    private String adress;

    @SerializedName("yetkili")
    private String adressOfficial;

    @SerializedName("tel")
    private String adressPhoneNumber;

    @SerializedName("google")
    private String adressGoogle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdressHeader() {
        return adressHeader;
    }

    public void setAdressHeader(String adressHeader) {
        this.adressHeader = adressHeader;
    }

    public String getAdressCity() {
        return adressCity;
    }

    public void setAdressCity(String adressCity) {
        this.adressCity = adressCity;
    }

    public String getAdressDistrict() {
        return adressDistrict;
    }

    public void setAdressDistrict(String adressDistrict) {
        this.adressDistrict = adressDistrict;
    }

    public String getAdressStreet() {
        return adressStreet;
    }

    public void setAdressStreet(String adressStreet) {
        this.adressStreet = adressStreet;
    }

    public String getAdressNeighborhood() {
        return adressNeighborhood;
    }

    public void setAdressNeighborhood(String adressNeighborhood) {
        this.adressNeighborhood = adressNeighborhood;
    }

    public String getAdressBuildingNo() {
        return adressBuildingNo;
    }

    public void setAdressBuildingNo(String adressBuildingNo) {
        this.adressBuildingNo = adressBuildingNo;
    }

    public String getAdressFloor() {
        return adressFloor;
    }

    public void setAdressFloor(String adressFloor) {
        this.adressFloor = adressFloor;
    }

    public String getAdressApertment() {
        return adressApertment;
    }

    public void setAdressApertment(String adressApertment) {
        this.adressApertment = adressApertment;
    }

    public String getAdressDescription() {
        return adressDescription;
    }

    public void setAdressDescription(String adressDescription) {
        this.adressDescription = adressDescription;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getAdressOfficial() {
        return adressOfficial;
    }

    public void setAdressOfficial(String adressOfficial) {
        this.adressOfficial = adressOfficial;
    }

    public String getAdressPhoneNumber() {
        return adressPhoneNumber;
    }

    public void setAdressPhoneNumber(String adressPhoneNumber) {
        this.adressPhoneNumber = adressPhoneNumber;
    }

    public String getAdressGoogle() {
        return adressGoogle;
    }

    public void setAdressGoogle(String adressGoogle) {
        this.adressGoogle = adressGoogle;
    }
}
