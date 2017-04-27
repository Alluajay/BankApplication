package com.example.allu.bank.POJO;

/**
 * Created by allu on 4/24/17.
 */

public class Customer {
    public int id;
    public String accno,name,pass,bank,branch,mobno,address,email,keyvalue;

    public Customer(String accno, String name, String pass, String bank, String branch, String mobno, String address, String email) {
        this.accno = accno;
        this.name = name;
        this.pass = pass;
        this.bank = bank;
        this.branch = branch;
        this.mobno = mobno;
        this.address = address;
        this.email = email;
    }

    public Customer(int id, String accno, String name, String pass, String bank, String branch, String mobno, String address, String email, String keyvalue) {
        this.id = id;
        this.accno = accno;
        this.name = name;
        this.pass = pass;
        this.bank = bank;
        this.branch = branch;
        this.mobno = mobno;
        this.address = address;
        this.email = email;
        this.keyvalue = keyvalue;
    }
}
