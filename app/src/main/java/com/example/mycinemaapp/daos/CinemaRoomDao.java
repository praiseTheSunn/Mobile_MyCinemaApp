package com.example.mycinemaapp.daos;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.mycinemaapp.models.CinemaRoomEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface CinemaRoomDao {
    @Query("SELECT cinema_rooms.roomLayoutPath FROM cinema_rooms, cinemas WHERE cinemas.id = :id")
    Flowable<List<String>> getRoomById(int id);
}
