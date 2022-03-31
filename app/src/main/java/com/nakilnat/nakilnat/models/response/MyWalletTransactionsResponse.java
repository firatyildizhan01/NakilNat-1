package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class MyWalletTransactionsResponse {
    @SerializedName("durum")
    private String myTransactionsState;

    @SerializedName("teklif_id")
    private String myTransactionsOfferId;

    @SerializedName("tarih")
    private String myTransactionsTime;

    @SerializedName("komisyon")
    private String myTransactionsCommission;

    @SerializedName("kazanc")
    private String myTransactionsEarn;

    public String getMyTransactionsState() {
        return myTransactionsState;
    }

    public void setMyTransactionsState(String myTransactionsState) {
        this.myTransactionsState = myTransactionsState;
    }

    public String getMyTransactionsOfferId() {
        return myTransactionsOfferId;
    }

    public void setMyTransactionsOfferId(String myTransactionsOfferId) {
        this.myTransactionsOfferId = myTransactionsOfferId;
    }

    public String getMyTransactionsTime() {
        return myTransactionsTime;
    }

    public void setMyTransactionsTime(String myTransactionsTime) {
        this.myTransactionsTime = myTransactionsTime;
    }

    public String getMyTransactionsCommission() {
        return myTransactionsCommission;
    }

    public void setMyTransactionsCommission(String myTransactionsCommission) {
        this.myTransactionsCommission = myTransactionsCommission;
    }

    public String getMyTransactionsEarn() {
        return myTransactionsEarn;
    }

    public void setMyTransactionsEarn(String myTransactionsEarn) {
        this.myTransactionsEarn = myTransactionsEarn;
    }
}
