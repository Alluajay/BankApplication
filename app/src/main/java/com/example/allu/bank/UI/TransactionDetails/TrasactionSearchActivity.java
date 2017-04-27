package com.example.allu.bank.UI.TransactionDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.allu.bank.Adapter.Adapter_transaction;
import com.example.allu.bank.POJO.Transaction;
import com.example.allu.bank.R;
import com.example.allu.bank.ServerUtils.AnamolousUtils;
import com.example.allu.bank.ServerUtils.TransactionUtils;
import com.example.allu.bank.Utils.Utils;

import java.util.ArrayList;

public class TrasactionSearchActivity extends AppCompatActivity implements View.OnClickListener{

    Utils utils;
    TransactionUtils transactionUtils;
    RecyclerView recyclerView;

    ArrayList<Transaction> transactionArrayList;
    Adapter_transaction adapter_transaction;

    EditText Edit_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trasaction_search);
        setTitle("Search Transaction");
        Edit_id = (EditText)findViewById(R.id.edit_search);

        utils = new Utils(this);


        recyclerView =(RecyclerView)findViewById(R.id.recy_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        adapter_transaction = new Adapter_transaction(transactionArrayList,this);
        transactionUtils = new TransactionUtils(this,recyclerView,adapter_transaction);
        transactionArrayList = new ArrayList<>();

        transactionUtils.getTransactionList();

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
        if(!utils.isEmptyString(idtxt)){
            id = Integer.parseInt(idtxt);
            transactionUtils.getTransactionList(id);
        }else{
            utils.Toast("Enter a search id");
        }
    }
}
