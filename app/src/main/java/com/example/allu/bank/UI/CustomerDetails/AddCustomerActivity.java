package com.example.allu.bank.UI.CustomerDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.allu.bank.POJO.Customer;
import com.example.allu.bank.R;
import com.example.allu.bank.ServerUtils.CustomerUtils;
import com.example.allu.bank.Utils.Utils;

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {
    Customer customer;

    Utils utils;
    CustomerUtils customerUtils;

    EditText Accno,Name,Password,Bank,Branch,Mobno,Address,Emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        setTitle("Create Customer");
        utils = new Utils(this);
        customerUtils = new CustomerUtils(this);

        Accno = (EditText)findViewById(R.id.edit_accno);
        Name = (EditText)findViewById(R.id.edit_name);
        Password = (EditText)findViewById(R.id.edit_pass);
        Bank = (EditText)findViewById(R.id.edit_bank);
        Branch = (EditText)findViewById(R.id.edit_branch);
        Mobno = (EditText)findViewById(R.id.edit_mobno);
        Address = (EditText)findViewById(R.id.edit_address);
        Emailid = (EditText)findViewById(R.id.edit_email);
    }

    void createCustomer(){
        String accno = Accno.getText().toString();
        String name = Name.getText().toString();
        String pass = Password.getText().toString();
        String bank = Bank.getText().toString();
        String branch = Branch.getText().toString();
        String mobno = Mobno.getText().toString();
        String address = Address.getText().toString();
        String email = Emailid.getText().toString();

        if(utils.isEmptyString(accno,name,pass,bank,branch,mobno,address,email)){
            customer = new Customer(accno,name,pass,bank,branch,mobno,address,email);
            customerUtils.createCustomer(customer);
        }else{
            utils.Toast("Insert all fields..");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_createcus:
                createCustomer();
                break;
            default:
                break;
        }
    }
}
