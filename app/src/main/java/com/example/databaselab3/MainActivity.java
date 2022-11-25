package com.example.databaselab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.databaselab3.Adapters.ItemAdapter;
import com.example.databaselab3.Adapters.SaleAdapter;
import com.example.databaselab3.Adapters.UserAdapter;
import com.example.databaselab3.DB.DatabaseClient;
import com.example.databaselab3.Models.Item;
import com.example.databaselab3.Models.Sale;
import com.example.databaselab3.Models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddUser, buttonAddItem, buttonAddSale;
    private RecyclerView recyclerViewUsers, recyclerViewItems, recyclerViewSales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewItems = findViewById(R.id.recyclerViewItems);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewSales = findViewById(R.id.recyclerViewSales);
        recyclerViewSales.setLayoutManager(new LinearLayoutManager(this));

        buttonAddUser = findViewById(R.id.addUser);
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                intent.putExtra("type", "USER");
                startActivity(intent);
            }
        });

        buttonAddItem = findViewById(R.id.addItem);
        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                intent.putExtra("type", "ITEM");
                startActivity(intent);
            }
        });

        buttonAddSale = findViewById(R.id.addSale);
        buttonAddSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                intent.putExtra("type", "SALE");
                startActivity(intent);
            }
        });

        getUsers();
        getItems();
        getSales();
    }

    private void getUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                UserAdapter adapter = new UserAdapter(MainActivity.this, users);
                recyclerViewUsers.setAdapter(adapter);
            }
        }

        GetUsers gu = new GetUsers();
        gu.execute();
    }

    private void getItems() {
        class GetItems extends AsyncTask<Void, Void, List<Item>> {

            @Override
            protected List<Item> doInBackground(Void... voids) {
                List<Item> itemList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .itemDao()
                        .getAll();
                return itemList;
            }

            @Override
            protected void onPostExecute(List<Item> items) {
                super.onPostExecute(items);
                ItemAdapter adapter = new ItemAdapter(MainActivity.this, items);
                recyclerViewItems.setAdapter(adapter);
            }
        }

        GetItems gu = new GetItems();
        gu.execute();
    }



    private void getSales() {
        class GetSales extends AsyncTask<Void, Void, List<Sale>> {

            @Override
            protected List<Sale> doInBackground(Void... voids) {
                List<Sale> saleList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .saleDao()
                        .getAll();
                return saleList;
            }

            @Override
            protected void onPostExecute(List<Sale> sales) {
                super.onPostExecute(sales);
                SaleAdapter adapter = new SaleAdapter(MainActivity.this, sales);
                recyclerViewSales.setAdapter(adapter);
            }
        }

        GetSales gu = new GetSales();
        gu.execute();
    }



}