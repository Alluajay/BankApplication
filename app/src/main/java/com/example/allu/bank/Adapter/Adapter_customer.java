package com.example.allu.bank.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allu.bank.POJO.Customer;
import com.example.allu.bank.R;

import java.util.ArrayList;

/**
 * Created by allu on 4/24/17.
 */

public class Adapter_customer extends RecyclerView.Adapter<ViewHolder_customer> {
    ArrayList<Customer> customerArrayList;
    Context context;

    public Adapter_customer(ArrayList<Customer> customerArrayList, Context context) {
        this.customerArrayList = customerArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder_customer onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_customer_layout,parent,false);
        return new ViewHolder_customer(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder_customer holder, int position) {
        Customer customer = customerArrayList.get(position);
        holder.Id.setText(customer.id+"");
        holder.Name.setText(customer.name);
        holder.Accno.setText(customer.accno);
        holder.Name.setText(customer.name);
        holder.Bank.setText(customer.bank);
        holder.Branch.setText(customer.branch);
        holder.Mobileno.setText(customer.mobno);
        holder.Address.setText(customer.address);
        holder.Email.setText(customer.email);
    }

    @Override
    public int getItemCount() {
        return customerArrayList.size();
    }
}
class ViewHolder_customer extends RecyclerView.ViewHolder{
    TextView Id,Accno,Name,Bank,Branch,Mobileno,Address,Email;
    public ViewHolder_customer(View itemView) {
        super(itemView);
        Id = (TextView)itemView.findViewById(R.id.txt_id);
        Accno = (TextView)itemView.findViewById(R.id.txt_accno);
        Name = (TextView)itemView.findViewById(R.id.txt_name);
        Bank = (TextView)itemView.findViewById(R.id.txt_bank);
        Branch = (TextView)itemView.findViewById(R.id.txt_branch);
        Mobileno = (TextView)itemView.findViewById(R.id.txt_mobno);
        Address = (TextView)itemView.findViewById(R.id.txt_address);
        Email = (TextView)itemView.findViewById(R.id.txt_email);
    }
}
