package com.example.databaselab3.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;



import com.example.databaselab3.Models.Item;
import com.example.databaselab3.Models.Sale;
import com.example.databaselab3.Models.User;

@Database(entities = {User.class, Item.class, Sale.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDao();
    public abstract ItemDAO itemDao();
    public abstract SaleDAO saleDao();
}
