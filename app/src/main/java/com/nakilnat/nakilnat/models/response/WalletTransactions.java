package com.nakilnat.nakilnat.models.response;

public class WalletTransactions {
    private String transactionTime;
    private String transactionDescription;
    private String transactionAmount;

    public WalletTransactions(String transactionTime, String transactionDescription, String transactionAmount) {
        this.transactionTime = transactionTime;
        this.transactionDescription = transactionDescription;
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
