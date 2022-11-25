package com.example.databaselab3.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaselab3.Models.Item;
import com.example.databaselab3.R;
import com.example.databaselab3.UpdateActivity;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemsViewHolder>{

    private Context mCtx;
    private List<Item> itemList;

    public ItemAdapter(Context mCtx, List<Item> itemList) {
        this.mCtx = mCtx;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.my_row, parent, false);
        //view.setMinimumHeight(parent.getMeasuredHeight() / 4);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        Item t = itemList.get(position);
        holder.textView1.setText(String.valueOf(t.getItem_id()));
        holder.textView2.setText(t.getItem_name());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView1, textView2, textViewVal3;

        public ItemsViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textCol1);
            textView2 = itemView.findViewById(R.id.textCol2);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = itemList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateActivity.class);
            intent.putExtra("item", item);
            intent.putExtra("type", "ITEM");

            mCtx.startActivity(intent);
        }
    }
}
