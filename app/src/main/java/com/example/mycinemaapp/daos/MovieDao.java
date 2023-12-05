package com.example.mycinemaapp.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mycinemaapp.models.MovieEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import kotlinx.coroutines.flow.Flow;

// MovieDao.java
@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    Flowable<List<MovieEntity>> getAllMovies();

//    @Insert(entity = MovieEntity.class, onConflict = OnConflictStrategy.REPLACE)
//    public Completable insertMovie(MovieEntity movie);

    @Insert(entity = MovieEntity.class)
    public void insertMovie(MovieEntity movie);

    @Query("SELECT COUNT(*) FROM movies")
    int getCount();

    @Query("SELECT * FROM movies WHERE rating >= 4.8")
    Flowable<List<MovieEntity>> getHotMovies();

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :newText || '%'")
    Flowable<List<MovieEntity>> getMoviesByNames(String newText);
}
