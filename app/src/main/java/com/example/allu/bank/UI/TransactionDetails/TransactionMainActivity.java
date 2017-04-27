package com.example.allu.bank.UI.TransactionDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.allu.bank.R;
import com.example.allu.bank.Utils.Utils;

public class TransactionMainActivity extends AppCompatActivity implements View.OnClickListener{
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Transactions");
        setContentView(R.layout.activity_transaction_main);
        utils = new Utils(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                utils.Goto(TrasactionSearchActivity.class);
                break;
            case R.id.btn_view:
                utils.Goto(TransactionViewActivity.class);
                break;
        }
    }
}
