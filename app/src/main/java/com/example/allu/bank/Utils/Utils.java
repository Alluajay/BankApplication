package com.example.allu.bank.Utils;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;


import com.example.allu.bank.POJO.User;
import com.example.allu.bank.UI.LoginActivity;
import com.google.gson.Gson;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;


/**
 * Created by allu on 2/14/17.
 */

public class Utils {

    String TAG = "Utils";
    Context mContext;
    SharedPreferences preferences;
    ProgressDialog progressDialog;

    Gson gson;
    public Utils(Context context){
        mContext = context;
        preferences = mContext.getSharedPreferences(DataAttributes.PREFERENCE_STRING,Context.MODE_PRIVATE);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Authenticating please wait");
        progressDialog.setCancelable(false);
        gson = new Gson();
    }

    public void Goto(Class cls){
        Intent i=new Intent(mContext,cls);
        mContext.startActivity(i);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void setProgressDialogMessage(String msg){
        progressDialog.setMessage(msg);
    }

    public void showDialog(){
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    public void closeDialog(){
        if(progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }

    public void Login(User user){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(DataAttributes.PREFERENCE_USER_OBJECT,user.getGson());
        editor.apply();
    }

    public void Logout(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(DataAttributes.PREFERENCE_USER_OBJECT);
        editor.apply();
        Goto(LoginActivity.class);
    }

    public User GetUserDetails(){
        if(LoginStatus()){
            String json = preferences.getString(DataAttributes.PREFERENCE_USER_OBJECT,"");
            User user = gson.fromJson(json,User.class);
            return user;
        }else{
            return null;
        }
    }

    public boolean isEmptyString(String... Args){
        for (String arg : Args){
            if(arg.trim().equals("") || arg == null){
                return false;
            }
        }
        return true;
    }

    public boolean isEmptyint(int... Args){
        for (int arg : Args){
            if(arg == 0){
                return false;
            }
        }
        return true;
    }

    public boolean isEmptylong(long... Args){
        for (long arg : Args){
            if(arg == 0){
                return false;
            }
        }
        return true;
    }

    public boolean LoginStatus(){
        if(preferences.contains(DataAttributes.PREFERENCE_USER_OBJECT)){
            return true;
        }
        return false;
    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;
                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }

    public void Toast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }

}
