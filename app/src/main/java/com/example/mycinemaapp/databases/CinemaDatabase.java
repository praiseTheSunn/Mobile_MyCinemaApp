package com.example.mycinemaapp.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mycinemaapp.daos.CinemaDao;
import com.example.mycinemaapp.daos.CinemaRoomDao;
import com.example.mycinemaapp.models.CinemaEntity;
import com.example.mycinemaapp.models.CinemaRoomEntity;

@Database(entities = {CinemaEntity.class, CinemaRoomEntity.class}, version = 1, exportSchema = false)
public abstract class CinemaDatabase extends RoomDatabase {
    public abstract CinemaDao cinemaDao();
    public abstract CinemaRoomDao cinemaRoomDao();

    private static CinemaDatabase instance;

    public static synchronized CinemaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            CinemaDatabase.class,
                            "cinema_database"
                    )
//                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }
}
