package com.nakilnat.nakilnat.models.request;

public class RegisterRequest {
    private String firma;
    private String cep_tel;
    private String eposta;
    private String sifre;

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getCep_tel() {
        return cep_tel;
    }

    public void setCep_tel(String cep_tel) {
        this.cep_tel = cep_tel;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
