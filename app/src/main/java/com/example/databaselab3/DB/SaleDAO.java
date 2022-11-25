package com.example.databaselab3.DB;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.databaselab3.Models.Item;
import com.example.databaselab3.Models.Sale;

import java.util.List;

@Dao
public interface SaleDAO {

    @Query("SELECT * FROM sale")
    List<Sale> getAll();

    @Insert
    void insert(Sale sale);

    @Delete
    void delete(Sale sale);

    @Update
    void update(Sale sale);
}
