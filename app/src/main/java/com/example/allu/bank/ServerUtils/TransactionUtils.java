package com.example.allu.bank.ServerUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.allu.bank.Adapter.Adapter_transaction;
import com.example.allu.bank.POJO.Customer;
import com.example.allu.bank.POJO.Transaction;
import com.example.allu.bank.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.allu.bank.Utils.URL.CUSTOMER_URL;
import static com.example.allu.bank.Utils.URL.TRANSACTION_URL;

/**
 * Created by allu on 4/23/17.
 */

public class TransactionUtils {
    String TAG = TransactionUtils.class.getSimpleName();
    Context context;
    RequestQueue queue;
    Utils utils;
    RecyclerView recyclerView;
    Adapter_transaction adapter_transaction;

    public TransactionUtils(Context context,RecyclerView recyclerView,Adapter_transaction adapter_transaction) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
        utils = new Utils(context);
        this.recyclerView = recyclerView;
        this.adapter_transaction = adapter_transaction;
    }

    public void getTransactionList(){
        utils.setProgressDialogMessage("Loading transactions..");
        utils.showDialog();

        final JSONObject param = new JSONObject();
        try {
            param.put("option","getalltransactions");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ArrayList<Transaction> transactionArrayList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, TRANSACTION_URL, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                utils.closeDialog();
                Log.e(TAG,response.toString());
                try {
                    if(response.getString("status").equals("success")){
                        JSONArray array = response.getJSONArray("records");
                        for(int i = 0;i < array.length();i++){
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("Transactionid");
                            String send = object.getString("senderaccountno");
                            String rec = object.getString("receiveraccountno");
                            String date = object.getString("DateTime");
                            String ipaddress = object.getString("ipaddress");
                            String amount = object.getString("amount");
                            Transaction transaction = new Transaction(id,send,rec,date,ipaddress,amount);
                            transactionArrayList.add(transaction);
                        }
                        adapter_transaction = new Adapter_transaction(transactionArrayList,context);
                        recyclerView.setAdapter(adapter_transaction);
                    }else {
                        utils.Toast(response.getString("message"));
                    }
                } catch (JSONException e) {
                    Log.e(TAG,"error in parsing json : "+e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utils.closeDialog();
                Log.e(TAG,"Volley error :"+error.toString());
                utils.Toast("Volley error");
            }
        });

        queue.add(request);
    }

    public void getTransactionList(int id){
        utils.setProgressDialogMessage("Loading transactions..");
        utils.showDialog();

        final JSONObject param = new JSONObject();
        try {
            param.put("option","searchtransaction");
            param.put("accno",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ArrayList<Transaction> transactionArrayList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, TRANSACTION_URL, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                utils.closeDialog();
                Log.e(TAG,response.toString());
                try {
                    if(response.getString("status").equals("success")){
                        JSONArray array = response.getJSONArray("records");
                        for(int i = 0;i < array.length();i++){
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("Transactionid");
                            String send = object.getString("senderaccountno");
                            String rec = object.getString("receiveraccountno");
                            String date = object.getString("DateTime");
                            String ipaddress = object.getString("ipaddress");
                            String amount = object.getString("amount");
                            Transaction transaction = new Transaction(id,send,rec,date,ipaddress,amount);
                            transactionArrayList.add(transaction);
                        }
                        adapter_transaction = new Adapter_transaction(transactionArrayList,context);
                        recyclerView.setAdapter(adapter_transaction);
                    }else {
                        utils.Toast(response.getString("message"));
                    }
                } catch (JSONException e) {
                    Log.e(TAG,"error in parsing json : "+e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utils.closeDialog();
                Log.e(TAG,"Volley error :"+error.toString());
                utils.Toast("Volley error");
            }
        });

        queue.add(request);
    }

}
