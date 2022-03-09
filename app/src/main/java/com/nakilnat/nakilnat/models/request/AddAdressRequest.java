package com.nakilnat.nakilnat.models.request;

public class AddAdressRequest {
    private String token;
    private String baslik;
    private String il;
    private String ilce;
    private String sokak;
    private String mahalle;
    private String bina;
    private String kat;
    private String daire;
    private String adresTarif;
    private String adres;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public String getSokak() {
        return sokak;
    }

    public void setSokak(String sokak) {
        this.sokak = sokak;
    }

    public String getMahalle() {
        return mahalle;
    }

    public void setMahalle(String mahalle) {
        this.mahalle = mahalle;
    }

    public String getBina() {
        return bina;
    }

    public void setBina(String bina) {
        this.bina = bina;
    }

    public String getKat() {
        return kat;
    }

    public void setKat(String kat) {
        this.kat = kat;
    }

    public String getDaire() {
        return daire;
    }

    public void setDaire(String daire) {
        this.daire = daire;
    }

    public String getAdresTarif() {
        return adresTarif;
    }

    public void setAdresTarif(String adresTarif) {
        this.adresTarif = adresTarif;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
