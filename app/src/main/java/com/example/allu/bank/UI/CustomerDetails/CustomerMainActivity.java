package com.example.allu.bank.UI.CustomerDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.allu.bank.R;
import com.example.allu.bank.Utils.Utils;

public class CustomerMainActivity extends AppCompatActivity implements View.OnClickListener {
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        setTitle("Customer Activity");
        utils = new Utils(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                utils.Goto(AddCustomerActivity.class);
                break;
            case R.id.btn_search:
                utils.Goto(SearchCustomerActivity.class);
                break;
            case R.id.btn_view:
                utils.Goto(ViewCustomersActivity.class);
                break;
        }
    }
}
