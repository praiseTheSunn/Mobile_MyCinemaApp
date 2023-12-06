package com.example.mycinemaapp.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.daos.UserDao;
import com.example.mycinemaapp.models.UserEntity;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao UserDao();
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UserDatabase.class,
                            "user_database"
                    )
//                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
}
