package com.example.allu.bank.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.allu.bank.POJO.Transaction;
import com.example.allu.bank.R;

import java.util.ArrayList;

/**
 * Created by allu on 4/24/17.
 */

public class Adapter_transaction extends RecyclerView.Adapter<ViewHolder_transaction> {
    private ArrayList<Transaction> transactionArrayList;
    private Context context;

    public Adapter_transaction(ArrayList<Transaction> transactionArrayList, Context context) {
        this.transactionArrayList = transactionArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder_transaction onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_transaction_layout,parent,false);
        return new ViewHolder_transaction(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_transaction holder, int position) {
        Transaction transaction = transactionArrayList.get(position);
        holder.Id.setText(transaction.id+"");
        holder.SenAcc.setText(transaction.senderAcc);
        holder.RecAcc.setText(transaction.receiverAcc);
        holder.TimeStamp.setText(transaction.Date);

        holder.IpAdd.setText(transaction.ipAdd);
        holder.Amount.setText(transaction.amount+"");
    }

    @Override
    public int getItemCount() {
        return transactionArrayList.size();
    }
}

class ViewHolder_transaction extends RecyclerView.ViewHolder{
    TextView Id,SenAcc,RecAcc,TimeStamp,IpAdd,Amount;
    public ViewHolder_transaction(View itemView) {
        super(itemView);
        Id = (TextView)itemView.findViewById(R.id.txt_id);
        SenAcc = (TextView)itemView.findViewById(R.id.txt_senderaccno);
        RecAcc = (TextView)itemView.findViewById(R.id.txt_receiveraccno);
        TimeStamp = (TextView)itemView.findViewById(R.id.txt_timestamp);
        IpAdd = (TextView)itemView.findViewById(R.id.txt_ipaddress);
        Amount = (TextView)itemView.findViewById(R.id.txt_amount);
    }
}
