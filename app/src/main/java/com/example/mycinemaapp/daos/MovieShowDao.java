package com.example.mycinemaapp.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mycinemaapp.models.MovieShowEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MovieShowDao {
    @Query("SELECT roomId FROM movie_shows " +
            "WHERE cinemaId = :cinemaId " +
            "AND showDate = :showDate " +
            "AND showTime = :showTime")
    Flowable<List<Integer>> getRoomIdByCinemaIdDateAndTime(int cinemaId, Date showDate, String showTime);

    @Insert(entity = MovieShowEntity.class)
    public void insertMovie(MovieShowEntity movie);

    @Query("SELECT COUNT(*) FROM movie_shows")
    Integer getCount();

    @Query("SELECT DISTINCT cinemaId FROM movie_shows WHERE showDate = :date AND movieId = :movieId")
    Flowable<List<Long>> getCinemaIdByDateAndMovieId(Date date, long movieId);

    @Query("SELECT DISTINCT showTime FROM movie_shows WHERE showDate = :date AND movieId = :movieId AND cinemaId = :cinemaId")
    Flowable<List<String>> getTimeByDateAndMovieIdAndCinemaId(Date date, long movieId, long cinemaId);

    @Query("SELECT DISTINCT showTime FROM movie_shows")
    Flowable<List<String>> getAllShowTimes();
}