package com.example.allu.bank.ServerUtils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.allu.bank.POJO.User;
import com.example.allu.bank.UI.LoginActivity;
import com.example.allu.bank.UI.MainActivity;
import com.example.allu.bank.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.allu.bank.Utils.URL.LOGIN_URL;

/**
 * Created by allu on 4/24/17.
 */

public class LoginUtils {
    String TAG = LoginUtils.class.getSimpleName();
    Context context;
    Utils utils;
    RequestQueue queue;

    public LoginUtils(Context context) {
        this.context = context;
        utils = new Utils(context);

        queue = Volley.newRequestQueue(context);
    }

    public void login(final String username, String password){
        utils.setProgressDialogMessage("Logging in..please wait..");
        utils.showDialog();

        JSONObject param = new JSONObject();
        try {
            param.put("option","login");
            param.put("username",username);
            param.put("pass",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                utils.closeDialog();
                Log.e(TAG,response.toString());
                try {
                    if(response.getString("status").equals("success")){
                        if(response.getInt("code") == 1){
                            String email = response.getString("mailid");
                            String phno = response.getString("phno");
                            String bank = response.getString("bank");
                            String branch = response.getString("branch");
                            User user = new User(username,email,phno,bank,branch);
                            utils.Toast(response.getString("message"));
                            utils.Goto(MainActivity.class);
                            utils.Login(user);
                        }else{
                            utils.Toast(response.getString("message"));
                        }
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

    public void signUp(User user,String pass){
        utils.setProgressDialogMessage("Logging in..please wait..");
        utils.showDialog();

        JSONObject param = new JSONObject();
        try {
            param.put("option","signup");
            param.put("name",user.name);
            param.put("pass",pass);
            param.put("mailid",user.email);
            param.put("phno",user.phno);
            param.put("bank",user.bank);
            param.put("branch",user.branch);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                utils.closeDialog();
                Log.e(TAG,response.toString());
                try {
                    utils.Toast(response.getString("message"));
                    if(response.getString("status").equals("success")){
                        utils.Goto(LoginActivity.class);
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
