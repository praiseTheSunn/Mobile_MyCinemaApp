package com.example.mycinemaapp.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mycinemaapp.daos.MovieShowDao;
import com.example.mycinemaapp.models.MovieShowEntity;

@Database(entities = {MovieShowEntity.class}, version = 1, exportSchema = false)
@TypeConverters({MovieShowEntity.DateConverter.class})
public abstract class MovieShowDatabase extends RoomDatabase  {

    public abstract MovieShowDao movieShowDao();

    private static MovieShowDatabase instance;

    public static synchronized MovieShowDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MovieShowDatabase.class,
                            "movie_show_database"
                    )
//                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
}
