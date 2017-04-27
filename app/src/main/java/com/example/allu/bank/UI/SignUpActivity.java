package com.example.allu.bank.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.allu.bank.POJO.User;
import com.example.allu.bank.R;
import com.example.allu.bank.ServerUtils.LoginUtils;
import com.example.allu.bank.Utils.Utils;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Name,Password,Mailid,Phno,Bank,Branch;
    LoginUtils loginUtils;

    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("New User");

        loginUtils = new LoginUtils(this);
        utils = new Utils(this);

        Name = (EditText)findViewById(R.id.edit_name);
        Password = (EditText)findViewById(R.id.edit_password);
        Mailid = (EditText)findViewById(R.id.edit_email);
        Phno = (EditText)findViewById(R.id.edit_mobno);
        Bank = (EditText)findViewById(R.id.edit_bank);
        Branch = (EditText)findViewById(R.id.edit_branch);
    }

    void signup(){
        String name = Name.getText().toString();
        String pass = Password.getText().toString();
        String mail = Mailid.getText().toString();
        String phn = Phno.getText().toString();
        String bank = Bank.getText().toString();
        String branch = Branch.getText().toString();

        if(utils.isEmptyString(name,pass,mail,phn,bank,branch)){
            User user = new User(name,mail,phn,bank,branch);
            loginUtils.signUp(user,pass);
        }else{
            utils.Toast("Enter all feilds");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup:
                signup();
                break;
        }
    }
}
