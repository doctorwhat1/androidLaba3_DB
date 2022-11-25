package com.example.databaselab3.DB;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.*;

import com.example.databaselab3.Models.Item;

import java.util.List;

//import androidx.room.ColumnInfo;
//import androidx.room.Entity;

//import java.io.Serializable;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM item")
    List<Item> getAll();

    @Insert
    void insert(Item item);

    @Delete
    void delete(Item item);

    @Update
    void update(Item item);

}
