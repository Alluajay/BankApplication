package com.example.allu.bank.Utils;

/**
 * Created by allu on 3/17/17.
 */

public abstract class URL {
    public static String IP = "anomalydetection.000webhostapp.com";
    public static String MAIN_URL = "https://"+IP+"/mobilebe/";

    public static String LOGIN_URL = MAIN_URL+"userauth.php";
    public static String ANOMOLY_URL = MAIN_URL+"anomoly.php";
    public static String TRANSACTION_URL = MAIN_URL+"transaction.php";
    public static String CUSTOMER_URL = MAIN_URL+"customer.php";



}
