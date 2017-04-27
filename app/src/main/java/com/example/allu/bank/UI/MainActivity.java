package com.example.allu.bank.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.allu.bank.R;
import com.example.allu.bank.UI.AnamolousDetails.AnamolousMainActivity;
import com.example.allu.bank.UI.CustomerDetails.CustomerMainActivity;
import com.example.allu.bank.UI.TransactionDetails.TransactionMainActivity;
import com.example.allu.bank.Utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils = new Utils(this);
    }

    @Override
    public void onBackPressed() {
        Log.e("tag",utils.LoginStatus()+"");
        if(!utils.LoginStatus()){
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_custdetails:
                utils.Goto(CustomerMainActivity.class);
                break;
            case R.id.btn_transdetails:
                utils.Goto(TransactionMainActivity.class);
                break;
            case R.id.btn_anamolousdetails:
                utils.Goto(AnamolousMainActivity.class);
                break;
            case R.id.btn_logout:
                utils.Logout();
                break;
        }
    }
}
