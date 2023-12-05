package com.example.mycinemaapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.example.mycinemaapp.parcelable.MovieShowParcelable;

import java.util.Date;

@Entity(tableName = "movie_shows")
public class MovieShowEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private Date showDate;
    String showTime;
    long cinemaId;
    long roomId;
    long movieId;

    // Constructor
    public MovieShowEntity(Date showDate, String showTime, long cinemaId, long roomId, long movieId) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.cinemaId = cinemaId;
        this.roomId = roomId;
        this.movieId = movieId;
    }

    public MovieShowEntity(MovieShowParcelable parcelable) {
        this.id = parcelable.getId();
        this.showDate = parcelable.getShowDate();
        this.showTime = parcelable.getShowTime();
        this.cinemaId = parcelable.getCinemaId();
        this.roomId = parcelable.getRoomId();
        this.movieId = parcelable.getMovieId();
    }

    // Getter methods
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getShowDate() {
        return showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public long getCinemaId() {
        return cinemaId;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setDate(Date date) {
        showDate = date;
    }

    public void setCinemaId(long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    // Type converter for Date
    public static class DateConverter {

        @TypeConverter
        public static Date toDate(Long timestamp) {
            return timestamp == null ? null : new Date(timestamp);
        }

        @TypeConverter
        public static Long toTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }
    }
}