package com.example.databaselab3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databaselab3.DB.DatabaseClient;
import com.example.databaselab3.Models.Item;
import com.example.databaselab3.Models.Sale;
import com.example.databaselab3.Models.User;

public class UpdateActivity extends AppCompatActivity {

    private EditText editTextValue1, editTextValue2, editTextValue3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editTextValue1 = findViewById(R.id.val1);
        editTextValue2 = findViewById(R.id.val2);
        editTextValue3 = findViewById(R.id.val3);

        final User user = (User) getIntent().getSerializableExtra("user");
        final Item item = (Item) getIntent().getSerializableExtra("item");
        final Sale sale = (Sale) getIntent().getSerializableExtra("sale");

        String type = (String) getIntent().getSerializableExtra("type");

        findViewById(R.id.imageDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Delete "+editTextValue2.getText()+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (type.contentEquals("USER"))
                        {deleteUser(user);}

                        if (type.contentEquals("ITEM"))
                        {deleteItem(item);}

                        if (type.contentEquals("SALE"))
                        {deleteSale(sale);}

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });

        // if user
        if (type.contentEquals("USER"))
        {loadUser(user);}
        // if item
        if (type.contentEquals("ITEM"))
        {loadItem(item);}

        if (type.contentEquals("SALE"))
        {loadSale(sale);}

        // if sale


        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                if (type.equals("USER"))
                {updateUser(user);}
                // if item
                if (type.equals("ITEM"))
                {updateItem(item);}
                //if sale
                if (type.equals("SALE"))
                {updateSale(sale);}
            }
        });
    }

    private void loadUser(User user) {
        editTextValue1.setText(String.valueOf(user.getUser_id()));
        editTextValue2.setText(user.getUser_name());
    }

    private void loadItem(Item item) {
        editTextValue1.setText(String.valueOf(item.getItem_id()));
        editTextValue2.setText(item.getItem_name());
    }

    private void loadSale(Sale sale) {
        editTextValue1.setText(String.valueOf(sale.getSale_id()));
        editTextValue2.setText(String.valueOf(sale.getItem_id()));
        editTextValue3.setText(sale.getUser_id());
    }

    private void updateUser(final User user) {
        final String sName = editTextValue2.getText().toString().trim();


        if (sName.isEmpty()) {
            editTextValue2.setError("First field required!");
            editTextValue2.requestFocus();
            return;
        }

        class UpdateUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                user.setUser_name(sName);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .update(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        }

        UpdateUser ut = new UpdateUser();
        ut.execute();
    }

    private void deleteUser(final User user) {
        class DeleteUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .delete(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        }

        DeleteUser du = new DeleteUser();
        du.execute();

    }

    private void updateItem(final Item item) {
        final String sName = editTextValue2.getText().toString().trim();


        if (sName.isEmpty()) {
            editTextValue2.setError("First field required!");
            editTextValue2.requestFocus();
            return;
        }

        class UpdateItem extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                item.setItem_name(sName);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .itemDao()
                        .update(item);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        }

        UpdateItem ut = new UpdateItem();
        ut.execute();
    }




    private void deleteItem(final Item item) {
        class DeleteItem extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .itemDao()
                        .delete(item);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        }

        DeleteItem du = new DeleteItem();
        du.execute();

    }



    private void updateSale(final Sale sale) {
        final String sName = editTextValue2.getText().toString().trim();
        final String sUserN = editTextValue3.getText().toString().trim();


        if (sName.isEmpty()) {
            editTextValue2.setError("First field required!");
            editTextValue2.requestFocus();
            return;
        }
        if (sUserN.isEmpty()) {
            editTextValue3.setError("Second field required!");
            editTextValue3.requestFocus();
            return;
        }

        class UpdateSale extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                sale.setItem_id(sName);
                sale.setUser_id(sUserN);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .saleDao()
                        .update(sale);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        }

        UpdateSale ut = new UpdateSale();
        ut.execute();
    }

    private void deleteSale(final Sale sale) {
        class DeleteSale extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .saleDao()
                        .delete(sale);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        }

        DeleteSale du = new DeleteSale();
        du.execute();

    }



}