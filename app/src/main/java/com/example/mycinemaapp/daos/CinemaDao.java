package com.example.mycinemaapp.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.mycinemaapp.models.CinemaEntity;
import com.example.mycinemaapp.models.CinemaWithCinemaRooms;
import com.example.mycinemaapp.models.MovieEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface CinemaDao {
    @Transaction
    @Query("SELECT * FROM cinemas")
    public List<CinemaWithCinemaRooms> getCinemaWithCinemaRooms();

    @Insert(entity = CinemaEntity.class)
    public void insertMovie(CinemaEntity movie);

    @Query("SELECT name FROM cinemas WHERE id = :id")
    String getCinemaNameById(long id);

    @Query("SELECT name FROM cinemas")
    Flowable<List<String>> getAllCinemaNames();

    @Query("SELECT id FROM cinemas")
    Flowable<List<Long>> getAllCinemaIds();
}
