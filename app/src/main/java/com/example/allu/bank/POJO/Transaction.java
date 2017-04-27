package com.example.allu.bank.POJO;

/**
 * Created by allu on 4/24/17.
 */

public class Transaction {
    public int id;
    public String senderAcc,receiverAcc,Date,ipAdd,amount;

    public Transaction(int id, String senderAcc, String receiverAcc, String date, String ipAdd, String amount) {
        this.id = id;
        this.senderAcc = senderAcc;
        this.receiverAcc = receiverAcc;
        Date = date;
        this.ipAdd = ipAdd;
        this.amount = amount;
    }
}
