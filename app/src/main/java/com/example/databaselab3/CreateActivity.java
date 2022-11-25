package com.example.databaselab3;

import androidx.appcompat.app.AppCompatActivity;

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


public class CreateActivity extends AppCompatActivity {

    private EditText editTextValue1, editTextValue2, editTextValue3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        editTextValue1 = findViewById(R.id.val1);
        editTextValue2 = findViewById(R.id.val2);
        editTextValue3 = findViewById(R.id.val3);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = (String) getIntent().getSerializableExtra("type");

                final String sVal2 = editTextValue2.getText().toString().trim();
                if (sVal2.isEmpty()) {
                    editTextValue2.setError("Field required!");
                    editTextValue2.requestFocus();
                    return;
                }

                if (type.equals("USER"))
                {saveUser();}

                if (type.equals("ITEM"))
                {saveItem();}

                if (type.equals("SALE"))
                {saveSale();}

            }
        });
    }

    private void saveUser() {
        final String sVal1 = editTextValue1.getText().toString().trim();
        final String sVal2 = editTextValue2.getText().toString().trim();
        final String sVal3 = editTextValue3.getText().toString().trim();

        class SaveUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                User user = new User();
                user.setUser_name(sVal2);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveUser st = new SaveUser();
        st.execute();
    }



    private void saveItem() {
        final String sVal1 = editTextValue1.getText().toString().trim();
        final String sVal2 = editTextValue2.getText().toString().trim();
        final String sVal3 = editTextValue3.getText().toString().trim();

        class SaveItem extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Item item = new Item();
                item.setItem_name(sVal2);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .itemDao()
                        .insert(item);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveItem st = new SaveItem();
        st.execute();
    }


    private void saveSale() {
        final String sVal1 = editTextValue1.getText().toString().trim();
        final String sVal2 = editTextValue2.getText().toString().trim();
        final String sVal3 = editTextValue3.getText().toString().trim();

        if (sVal3.isEmpty()) {
            editTextValue3.setError("Field required!");
            editTextValue3.requestFocus();
            return;
        }

        class SaveSale extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Sale sale = new Sale();
                sale.setItem_id(sVal2);
                sale.setUser_id(sVal3);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .saleDao()
                        .insert(sale);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveSale st = new SaveSale();
        st.execute();
    }





}