package com.example.mycinemaapp.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.models.MovieEntity;

// MovieDatabase.java
@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDatabase instance;

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MovieDatabase.class,
                    "movie_database"
            )
//                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
}
