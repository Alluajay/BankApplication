package com.example.allu.bank.UI.AnamolousDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.allu.bank.R;
import com.example.allu.bank.Utils.Utils;

public class AnamolousMainActivity extends AppCompatActivity implements View.OnClickListener {
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Anamolous Transactions");
        setContentView(R.layout.activity_anamolous_main);
        utils = new Utils(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                utils.Goto(AnamolousSearchActivity.class);
                break;
            case R.id.btn_view:
                utils.Goto(AnamolousViewActivity.class);
                break;
        }
    }
}
