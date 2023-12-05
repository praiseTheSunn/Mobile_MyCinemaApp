package com.example.mycinemaapp.models;

import androidx.room.Embedded;
import androidx.room.Query;
import androidx.room.Relation;
import androidx.room.Transaction;

import java.util.List;

public class CinemaWithCinemaRooms {
    @Embedded
    public CinemaEntity user;
    @Relation(
            parentColumn = "id",
            entityColumn = "cinemaId"
    )
    public List<CinemaRoomEntity> playlists;
}
