package com.nakilnat.nakilnat.models.response;

import com.google.gson.annotations.SerializedName;

public class TotalAmountResponse {
    @SerializedName("bakiye")
    private String amount;

    public TotalAmountResponse(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
