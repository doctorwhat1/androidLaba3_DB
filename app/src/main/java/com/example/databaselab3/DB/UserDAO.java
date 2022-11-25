package com.example.databaselab3.DB;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.databaselab3.Models.Item;
import com.example.databaselab3.Models.User;

import java.util.List;

//import androidx.room.ColumnInfo;
//import androidx.room.Entity;

//import java.io.Serializable;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

}
