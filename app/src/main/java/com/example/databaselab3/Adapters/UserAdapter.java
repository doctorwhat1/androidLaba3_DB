package com.example.databaselab3.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaselab3.Models.User;
import com.example.databaselab3.R;
import com.example.databaselab3.UpdateActivity;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder>{

    private Context mCtx;
    private List<User> userList;

    public UserAdapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.my_row, parent, false);
        //view.setMinimumHeight(parent.getMeasuredHeight() / 4);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        User t = userList.get(position);
        holder.textView1.setText(String.valueOf(t.getUser_id()));
        holder.textView2.setText(t.getUser_name());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView1, textView2, textViewVal3;

        public UsersViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textCol1);
            textView2 = itemView.findViewById(R.id.textCol2);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            User user = userList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateActivity.class);
            intent.putExtra("user", user);
            intent.putExtra("type", "USER");

            mCtx.startActivity(intent);
        }
    }
}
