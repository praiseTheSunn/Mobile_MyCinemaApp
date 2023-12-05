package com.example.mycinemaapp.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mycinemaapp.models.MovieShowEntity;

import java.util.Date;

public class MovieShowParcelable implements Parcelable {

    private long id;
    private Date showDate;
    private String showTime;
    private long cinemaId;
    private long roomId;
    private long movieId;

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

    // Constructor
    public MovieShowParcelable(Date showDate, String showTime, long cinemaId, long roomId, long movieId) {
        this.showDate = showDate;
        this.showTime = showTime;
        this.cinemaId = cinemaId;
        this.roomId = roomId;
        this.movieId = movieId;
    }

    public MovieShowParcelable(MovieShowEntity movieShowEntity) {
        this.id = movieShowEntity.getId();
        this.showDate = movieShowEntity.getShowDate();
        this.showTime = movieShowEntity.getShowTime();
        this.cinemaId = movieShowEntity.getCinemaId();
        this.roomId = movieShowEntity.getRoomId();
        this.movieId = movieShowEntity.getMovieId();
    }


    protected MovieShowParcelable(Parcel in) {
        id = in.readLong();
        showDate = new Date(in.readLong());
        showTime = in.readString();
        cinemaId = in.readLong();
        roomId = in.readLong();
        movieId = in.readLong();
    }

    public static final Creator<MovieShowParcelable> CREATOR = new Creator<MovieShowParcelable>() {
        @Override
        public MovieShowParcelable createFromParcel(Parcel in) {
            return new MovieShowParcelable(in);
        }

        @Override
        public MovieShowParcelable[] newArray(int size) {
            return new MovieShowParcelable[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(showDate.getTime());
        dest.writeString(showTime);
        dest.writeLong(cinemaId);
        dest.writeLong(roomId);
        dest.writeLong(movieId);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
