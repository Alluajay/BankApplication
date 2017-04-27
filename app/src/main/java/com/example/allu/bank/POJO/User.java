package com.example.allu.bank.POJO;

import com.google.gson.Gson;

/**
 * Created by allu on 4/22/17.
 */

public class User {
    public String name,email,phno,bank,branch;

    public User(String name, String email, String phno, String bank, String branch) {
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.bank = bank;
        this.branch = branch;
    }

    public String getGson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
