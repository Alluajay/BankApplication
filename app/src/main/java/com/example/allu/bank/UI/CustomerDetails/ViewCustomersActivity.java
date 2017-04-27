package com.example.allu.bank.UI.CustomerDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.allu.bank.Adapter.Adapter_customer;
import com.example.allu.bank.POJO.Customer;
import com.example.allu.bank.R;
import com.example.allu.bank.ServerUtils.CustomerUtils;
import com.example.allu.bank.Utils.Utils;

import java.util.ArrayList;

public class ViewCustomersActivity extends AppCompatActivity {

    Utils utils;
    CustomerUtils customerUtils;
    RecyclerView recyclerView;
    ArrayList<Customer> customerArrayList;
    Adapter_customer adapter_customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);
        setTitle("Customer list");
        recyclerView =(RecyclerView)findViewById(R.id.recy_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter_customer);
        utils = new Utils(this);

        adapter_customer = new Adapter_customer(customerArrayList,this);
        customerUtils = new CustomerUtils(this,recyclerView,adapter_customer);
        customerUtils.getCustomers();
    }
}
