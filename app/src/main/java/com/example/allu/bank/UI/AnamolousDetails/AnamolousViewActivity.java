package com.example.allu.bank.UI.AnamolousDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.allu.bank.Adapter.Adapter_transaction;
import com.example.allu.bank.POJO.Transaction;
import com.example.allu.bank.R;
import com.example.allu.bank.ServerUtils.AnamolousUtils;
import com.example.allu.bank.Utils.Utils;

import java.util.ArrayList;

public class AnamolousViewActivity extends AppCompatActivity {

    Utils utils;
    AnamolousUtils anamolousUtils;
    RecyclerView recyclerView;

    ArrayList<Transaction> transactionArrayList;
    Adapter_transaction adapter_transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anamolous_view);
        setTitle("Anamolous Transactions List");

        utils = new Utils(this);

        recyclerView =(RecyclerView)findViewById(R.id.recy_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(adapter_transaction);

        transactionArrayList = new ArrayList<>();
        adapter_transaction = new Adapter_transaction(transactionArrayList,this);
        anamolousUtils = new AnamolousUtils(this,recyclerView,adapter_transaction);
        anamolousUtils.getTransactionList();

    }
}
