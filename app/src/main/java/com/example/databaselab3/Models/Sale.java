package com.example.databaselab3.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Sale implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int sale_id;

    @ColumnInfo(name = "item_id")
    private String item_id;

    @ColumnInfo(name = "user_id")
    private String user_id;

    public int getSale_id() {
        return sale_id;
    }

    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
