package com.example.allu.bank.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.allu.bank.R;
import com.example.allu.bank.ServerUtils.LoginUtils;
import com.example.allu.bank.Utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Utils utils;
    LoginUtils loginUtils;
    EditText Username,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        utils = new Utils(this);
        loginUtils = new LoginUtils(this);
        Username = (EditText)findViewById(R.id.edit_username);
        Password = (EditText)findViewById(R.id.edit_password);
        if(utils.LoginStatus()){
            utils.Goto(MainActivity.class);
        }

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                Login();
                break;
            case R.id.btn_signup:
                utils.Goto(SignUpActivity.class);
                break;
        }
    }

    void Login(){
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        if(!utils.isEmptyString(username,password)){
            utils.Toast("Enter Username/Password");
            return;
        }
        loginUtils.login(username,password);
    }
}
