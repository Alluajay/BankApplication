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
import com.example.allu.bank.Adapter.Adapter_customer;
import com.example.allu.bank.POJO.Customer;
import com.example.allu.bank.UI.CustomerDetails.CustomerMainActivity;
import com.example.allu.bank.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.allu.bank.Utils.URL.CUSTOMER_URL;
import static com.example.allu.bank.Utils.URL.LOGIN_URL;

/**
 * Created by allu on 4/23/17.
 */

public class CustomerUtils {
    String TAG = CustomerUtils.class.getSimpleName();
    Context context;
    RequestQueue queue;
    Utils utils;

    RecyclerView recyclerView;
    Adapter_customer adapter_customer;

    public CustomerUtils(Context context, RecyclerView recyclerView, Adapter_customer adapter_customer) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
        utils = new Utils(context);
        this.recyclerView = recyclerView;
        this.adapter_customer = adapter_customer;
    }

    public CustomerUtils(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
        utils = new Utils(context);
    }


    public void createCustomer(Customer customer){
        utils.setProgressDialogMessage("Inserting customer..please wait..");
        utils.showDialog();

        final JSONObject param = new JSONObject();
        try {
            param.put("option","insert");
            param.put("accno",customer.accno);
            param.put("name",customer.name);
            param.put("pass",customer.pass);
            param.put("bank",customer.bank);
            param.put("branch",customer.branch);
            param.put("mobno",customer.mobno);
            param.put("address",customer.address);
            param.put("email",customer.email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, CUSTOMER_URL, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                utils.closeDialog();
                Log.e(TAG,response.toString());
                try {
                    utils.Toast(response.getString("message"));
                    if(response.getString("status").equals("success")){
                        utils.Goto(CustomerMainActivity.class);
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

    public void getCustomers(){
        utils.setProgressDialogMessage("Loading customer..");
        utils.showDialog();

        final JSONObject param = new JSONObject();
        try {
            param.put("option","getCustomers");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ArrayList<Customer> customerArrayList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, CUSTOMER_URL, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                utils.closeDialog();
                Log.e(TAG,response.toString());
                try {
                    if(response.getString("status").equals("success")){
                        JSONArray array = response.getJSONArray("records");
                        for(int i = 0;i < array.length();i++){
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("Id");
                            String accountno = object.getString("accountno");
                            String password = object.getString("password");
                            String name = object.getString("name");
                            String bank = object.getString("bank");
                            String branch = object.getString("branch");
                            String mobileno = object.getString("mobileno");
                            String address = object.getString("address");
                            String emailid = object.getString("emailid");
                            String keyval = object.getString("keysvalue");
                            Customer customer = new Customer(id,accountno,name,password,bank,branch,mobileno,address,emailid,keyval);
                            customerArrayList.add(customer);
                        }
                        adapter_customer = new Adapter_customer(customerArrayList,context);
                        recyclerView.setAdapter(adapter_customer);
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

    public void getCustomer(int id){
        utils.setProgressDialogMessage("Loading customer..");
        utils.showDialog();

        final JSONObject param = new JSONObject();
        try {
            param.put("option","searchCustomers");
            param.put("accno",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ArrayList<Customer> customerArrayList = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, CUSTOMER_URL, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                utils.closeDialog();
                Log.e(TAG,response.toString());
                try {
                    if(response.getString("status").equals("success")){
                        JSONArray array = response.getJSONArray("records");
                        for(int i = 0;i < array.length();i++){
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("Id");
                            String accountno = object.getString("accountno");
                            String password = object.getString("password");
                            String name = object.getString("name");
                            String bank = object.getString("bank");
                            String branch = object.getString("branch");
                            String mobileno = object.getString("mobileno");
                            String address = object.getString("address");
                            String emailid = object.getString("emailid");
                            String keyval = object.getString("keysvalue");
                            Customer customer = new Customer(id,accountno,name,password,bank,branch,mobileno,address,emailid,keyval);
                            customerArrayList.add(customer);
                        }
                        adapter_customer = new Adapter_customer(customerArrayList,context);
                        recyclerView.setAdapter(adapter_customer);
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
