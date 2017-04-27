package com.example.allu.bank.UI.CustomerDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.allu.bank.Adapter.Adapter_customer;
import com.example.allu.bank.Adapter.Adapter_transaction;
import com.example.allu.bank.POJO.Customer;
import com.example.allu.bank.R;
import com.example.allu.bank.ServerUtils.CustomerUtils;
import com.example.allu.bank.Utils.Utils;

import java.util.ArrayList;

public class SearchCustomerActivity extends AppCompatActivity implements View.OnClickListener {
    Utils utils;
    RecyclerView recyclerView;
    ArrayList<Customer> customerArrayList;
    EditText Edit_id;

    CustomerUtils customerUtils;
    Adapter_customer adapter_customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);
        setTitle("Search Customer");
        utils = new Utils(this);

        recyclerView =(RecyclerView)findViewById(R.id.recy_list);
        Edit_id = (EditText)findViewById(R.id.edit_search);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        adapter_customer = new Adapter_customer(customerArrayList,this);

        customerUtils = new CustomerUtils(this,recyclerView,adapter_customer);
        customerUtils.getCustomers();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_search:
                search();
                break;
        }
    }

    void search(){
        int id = 0;
        String idtxt = Edit_id.getText().toString();
        Log.e("search",idtxt);
        if(utils.isEmptyString(idtxt)){
            id = Integer.parseInt(Edit_id.getText().toString());
            customerUtils.getCustomer(id);

        }else{
            utils.Toast("Enter a search id");
        }
    }
}
