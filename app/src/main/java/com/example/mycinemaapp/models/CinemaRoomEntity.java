package com.example.mycinemaapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cinema_rooms", primaryKeys = {"roomId", "cinemaId"} )
public class CinemaRoomEntity {

    long roomId;
    long cinemaId;

    String roomLayoutPath;


    public long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(long id) {
        cinemaId = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long id) {
        roomId = id;
    }

    public String getRoomLayoutPath() {
        return roomLayoutPath;
    }

    public void setRoomLayoutPath(String roomLayoutPath) {
        this.roomLayoutPath = roomLayoutPath;
    }
}
