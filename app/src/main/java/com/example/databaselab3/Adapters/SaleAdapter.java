package com.example.databaselab3.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaselab3.Models.Sale;
import com.example.databaselab3.R;
import com.example.databaselab3.UpdateActivity;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SalesViewHolder>{

    private Context mCtx;
    private List<Sale> saleList;

    public SaleAdapter(Context mCtx, List<Sale> saleList) {
        this.mCtx = mCtx;
        this.saleList = saleList;
    }

    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.my_row, parent, false);
        return new SalesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SalesViewHolder holder, int position) {
        Sale t = saleList.get(position);
        holder.textView1.setText(String.valueOf(t.getSale_id()));
        holder.textView2.setText(t.getItem_id());
        holder.textView3.setText(t.getUser_id());

    }

    @Override
    public int getItemCount() {
        return saleList.size();
    }

    class SalesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView1, textView2, textView3;

        public SalesViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textCol1);
            textView2 = itemView.findViewById(R.id.textCol2);
            textView3 = itemView.findViewById(R.id.textCol3);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Sale sale = saleList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateActivity.class);
            intent.putExtra("sale", sale);
            intent.putExtra("type", "SALE");

            mCtx.startActivity(intent);
        }
    }
}
