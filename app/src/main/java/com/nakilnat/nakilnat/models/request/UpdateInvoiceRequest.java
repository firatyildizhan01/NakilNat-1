package com.nakilnat.nakilnat.models.request;

public class UpdateInvoiceRequest {
    private String token;
    private String unvan;
    private String adres;
    private String vergidaire;
    private String vergino;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getVergidaire() {
        return vergidaire;
    }

    public void setVergidaire(String vergidaire) {
        this.vergidaire = vergidaire;
    }

    public String getVergino() {
        return vergino;
    }

    public void setVergino(String vergino) {
        this.vergino = vergino;
    }
}
